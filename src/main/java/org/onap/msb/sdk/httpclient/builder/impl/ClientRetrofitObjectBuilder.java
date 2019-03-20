/*******************************************************************************
 * Copyright 2017-2018 ZTE, Inc. and others.
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
package org.onap.msb.sdk.httpclient.builder.impl;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

import org.onap.msb.sdk.discovery.entity.MicroServiceFullInfo;
import org.onap.msb.sdk.discovery.entity.NodeInfo;
import org.onap.msb.sdk.httpclient.RetrofitServiceUtils;
import org.onap.msb.sdk.httpclient.ServiceHttpEndPointBeanObject;
import org.onap.msb.sdk.httpclient.ServiceHttpEndPointObject;
import org.onap.msb.sdk.httpclient.builder.IRetrofitObjectBuilder;
import org.onap.msb.sdk.httpclient.exception.RetrofitServiceRuntimeException;
import org.onap.msb.sdk.httpclient.handler.RetrofitServiceHandlerContext;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * @author 10071214
 *
 */
public class ClientRetrofitObjectBuilder implements IRetrofitObjectBuilder {


    private RetrofitServiceHandlerContext context;


    public ClientRetrofitObjectBuilder(RetrofitServiceHandlerContext context) {
        super();
        this.context = context;
    }


    /*
     * (non-Javadoc)
     *
     * @see
     * com.zte.ums.zenap.httpclient.retrofit.builder.IRetrofitObjectBuilder#buildRetrofitObject(
     * java. util.concurrent.atomic.AtomicReference)
     */
    @Override
    public Map<ServiceHttpEndPointObject, Object> buildRetrofitObject(
                    AtomicReference<Map<ServiceHttpEndPointObject, Object>> endPointToRetrofitRef,
                    ServiceHttpEndPointObject lastEndPoint) throws RetrofitServiceRuntimeException {

        Map<ServiceHttpEndPointObject, Object> srvEndPointToRetrofit = endPointToRetrofitRef.get();
        if (srvEndPointToRetrofit == null) {
            srvEndPointToRetrofit = new LinkedHashMap<>();
            try {


                ServiceHttpEndPointBeanObject srvhttpEndPointBeanObject = context.getServiceHttpEndPointBeanObject();

                // MsbClientFactory msbClient =
                // context.getLocator().getService(MsbClientFactory.class);

                MicroServiceFullInfo fullInfo = null;

                fullInfo = context.getMsbClient().queryMicroServiceInfo(srvhttpEndPointBeanObject.getServiceName(),
                                srvhttpEndPointBeanObject.getServiceVersion());


                for (NodeInfo nodeInfo : fullInfo.getNodes()) {

                    MicroServiceFullInfo cloneFullInfo = cloneFullInfo(fullInfo, nodeInfo);

                    ServiceHttpEndPointObject endPointObj = new ServiceHttpEndPointObject(
                                    srvhttpEndPointBeanObject.getServiceName(),
                                    srvhttpEndPointBeanObject.getServiceVersion(), nodeInfo, cloneFullInfo);

                    String baseUrl = null;
                    if (fullInfo.getUrl() == null || fullInfo.getUrl().trim().length() == 0
                                    || fullInfo.getUrl().equals("/")) {
                        baseUrl = String.format("http://%s:%s/", nodeInfo.getIp(), nodeInfo.getPort());
                    } else {
                        baseUrl = String.format("http://%s:%s%s/", nodeInfo.getIp(), nodeInfo.getPort(),
                                        fullInfo.getUrl());
                    }

                    OkHttpClient httpClient = null;

                    if (context.getHttpClientConf() != null) {
                        if (srvhttpEndPointBeanObject.getClientProtocl().toLowerCase().equals("https")) {
                            httpClient = RetrofitServiceUtils.buildDefaultOkHttpsClient(context.getHttpClientConf());
                        } else {
                            httpClient = RetrofitServiceUtils.buildDefaultOkHttpClient(context.getHttpClientConf());
                        }
                    } else {
                        if (srvhttpEndPointBeanObject.getClientProtocl().toLowerCase().equals("https")) {
                            httpClient = RetrofitServiceUtils.buildDefaultOkHttpsClient(
                                            RetrofitServiceHandlerContext.getGlobalHttpClientConf());
                        } else {
                            httpClient = RetrofitServiceUtils.buildDefaultOkHttpClient(
                                            RetrofitServiceHandlerContext.getGlobalHttpClientConf());
                        }
                    }



                    Retrofit retrofit = new Retrofit.Builder().client(httpClient).baseUrl(baseUrl)
                                    .addConverterFactory(context.getConverterFactoryBuilder().buildConverterFactory())
                                    .build();

                    srvEndPointToRetrofit.put(endPointObj, retrofit.create(context.getRetrofitSrvInterfaceClazz()));

                }

                if (srvEndPointToRetrofit.isEmpty()) {
                    throw new RetrofitServiceRuntimeException("can't find service in msb,serviceName:"
                                    + srvhttpEndPointBeanObject.getServiceName() + ",serviceVersion:"
                                    + srvhttpEndPointBeanObject.getServiceVersion());
                }

                if (lastEndPoint != null) {
                    srvEndPointToRetrofit.remove(lastEndPoint);
                }

                if (srvEndPointToRetrofit.isEmpty()) {
                    throw new RetrofitServiceRuntimeException("can't find other service in msb,serviceName:"
                                    + srvhttpEndPointBeanObject.getServiceName() + ",serviceVersion:"
                                    + srvhttpEndPointBeanObject.getServiceVersion());
                }

                if (endPointToRetrofitRef.compareAndSet(null, srvEndPointToRetrofit)) {
                    context.setLastUpdateMsbTime(System.currentTimeMillis());
                }

                return endPointToRetrofitRef.get();


            } catch (Exception e) {
                throw new RetrofitServiceRuntimeException("init Retrofit service map fail", e);
            }

        } else {
            return endPointToRetrofitRef.get();
        }
    }


    private MicroServiceFullInfo cloneFullInfo(MicroServiceFullInfo fullInfo, NodeInfo nodeInfo) {

        MicroServiceFullInfo cloneFuleInfo = new MicroServiceFullInfo();
        cloneFuleInfo.setMetadata(fullInfo.getMetadata());
        cloneFuleInfo.setProtocol(fullInfo.getProtocol());
        cloneFuleInfo.setServiceName(fullInfo.getServiceName());
        cloneFuleInfo.setStatus(fullInfo.getStatus());
        cloneFuleInfo.setUrl(fullInfo.getUrl());
        cloneFuleInfo.setVersion(fullInfo.getVersion());
        cloneFuleInfo.setVisualRange(fullInfo.getVisualRange());
        cloneFuleInfo.setEnable_ssl(fullInfo.isEnable_ssl());
        Set<NodeInfo> nodeInfos = new HashSet<>();
        nodeInfos.add(nodeInfo);
        cloneFuleInfo.setNodes(nodeInfos);
        return cloneFuleInfo;
    }

}
