/*******************************************************************************
 * Copyright 2017 ZTE, Inc. and others.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
/**
 * 
 */
package org.onap.msb.sdk.httpclient.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import org.onap.msb.sdk.httpclient.ProxyRetrofitCall;
import org.onap.msb.sdk.httpclient.ServiceHttpEndPointObject;
import org.onap.msb.sdk.httpclient.exception.RetrofitServiceRuntimeException;
import org.onap.msb.sdk.httpclient.lb.LoadBalanceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import retrofit2.Call;

/**
 * @author 10071214
 *
 */
public class RetrofitServiceHandler implements InvocationHandler {

    private final static Logger logger = LoggerFactory.getLogger(RetrofitServiceHandler.class);
    private static long periodTime = 60;

    static {
        try {
            String periodStr = System.getenv("retrofit_route_cache_refresh_period");
            periodTime = periodStr != null ? Long.valueOf(periodStr) : 60;
            logger.info("retrofit_route_cache_refresh_period:" + periodTime);
        } catch (Exception e) {
            logger.warn("", e);
        }

    }



    private RetrofitServiceHandlerContext flowContext;

    private AtomicReference<Map<ServiceHttpEndPointObject, Object>> endPointToRetrofitRef = new AtomicReference();

    public RetrofitServiceHandler(RetrofitServiceHandlerContext flowContext) {
        super();
        this.flowContext = flowContext;
        logger.info("retrofit_route_cache_refresh_period:" + periodTime);
    }


    /*
     * (non-Javadoc)
     * 
     * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method,
     * java.lang.Object[])
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Object retrofitObject = null;
        ServiceHttpEndPointObjectWapper wapper = null;

        updateMsbInfo();
        wapper = selectRetrofitObjectByLBStrategy(
                        flowContext.getRetrofitObjectBuilder().buildRetrofitObject(endPointToRetrofitRef, null), method,
                        args);
        retrofitObject = wapper.retrofitObject;

        Object resultObjecct = method.invoke(retrofitObject, args);

        if (resultObjecct instanceof Call) {
            Call targetCall = (Call) resultObjecct;
            return new ProxyRetrofitCall(targetCall, this, wapper.endPoint, proxy, method, args);
        }
        return resultObjecct;
    }


    public Object reInvoke(Object proxy, Method method, Object[] args, ServiceHttpEndPointObject endPoint)
                    throws Throwable {


        Object retrofitObject = null;
        ServiceHttpEndPointObjectWapper wapper = null;

        updateMsbInfo();

        Map<ServiceHttpEndPointObject, Object> serviceHttpEndPointObjectMap =
                        flowContext.getRetrofitObjectBuilder().buildRetrofitObject(endPointToRetrofitRef, endPoint);

        wapper = selectRetrofitObjectByLBStrategy(serviceHttpEndPointObjectMap, method, args);



        retrofitObject = wapper.retrofitObject;

        Object resultObjecct = method.invoke(retrofitObject, args);

        return resultObjecct;

    }

    private void updateMsbInfo() {



        if (System.currentTimeMillis() - flowContext.getLastUpdateMsbTime() > periodTime * 1000) {
            clean();
        }
    }

    public void clean() {
        endPointToRetrofitRef.set(null);
    }


    private ServiceHttpEndPointObjectWapper selectRetrofitObjectByLBStrategy(
                    Map<ServiceHttpEndPointObject, Object> srvEndPointToRetrofit, Method method, Object[] args)
                    throws RetrofitServiceRuntimeException {

        LoadBalanceContext ctx = new LoadBalanceContext();
        ctx.setEndPoints(Lists.newArrayList(srvEndPointToRetrofit.keySet()));
        ctx.setArgs(args);
        ctx.setMethod(method);
        ServiceHttpEndPointObject endPoint = flowContext.getLbStrategy().chooseEndPointObject(ctx);
        return new ServiceHttpEndPointObjectWapper(endPoint, srvEndPointToRetrofit.get(endPoint));
    }

}


class ServiceHttpEndPointObjectWapper {

    protected ServiceHttpEndPointObject endPoint;
    protected Object retrofitObject;

    public ServiceHttpEndPointObjectWapper(ServiceHttpEndPointObject endPoint, Object retrofitObject) {
        super();
        this.endPoint = endPoint;
        this.retrofitObject = retrofitObject;
    }

}
