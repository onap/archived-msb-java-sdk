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
package org.onap.msb.sdk.httpclient.handler.impl;

import org.onap.msb.sdk.httpclient.ServiceHttpEndPointBeanObject;
import org.onap.msb.sdk.httpclient.annotaion.ServiceHttpEndPoint;
import org.onap.msb.sdk.httpclient.exception.RetrofitServiceRuntimeException;
import org.onap.msb.sdk.httpclient.handler.HandlerContextBuilder;
import org.onap.msb.sdk.httpclient.handler.RetrofitServiceHandlerContext;

/**
 * @author 10071214
 *
 */
public class ServiceHttpEndPointBeanObjectBuilder implements HandlerContextBuilder {

  /*
   * (non-Javadoc)
   *
   * @see
   * com.zte.ums.zenap.versey.rpc.retrofit2.service.impl.handler.HandlerContextBuilder#build(com.zte
   * .ums.zenap.versey.rpc.retrofit2.service.impl.handler.RetrofitServiceHandlerContext)
   */
  @Override
  public void build(RetrofitServiceHandlerContext ctx) throws RetrofitServiceRuntimeException {

    if (ctx.getServiceHttpEndPointBeanObject() == null) {
      ServiceHttpEndPointBeanObject beanObject =
          buildBeanObjectFromClassAnnotion(ctx.getRetrofitSrvInterfaceClazz());
      ctx.setServiceHttpEndPointBeanObject(beanObject);
    }


  }


  private ServiceHttpEndPointBeanObject buildBeanObjectFromClassAnnotion(
      Class<?> retrofitSrvInterfaceClazz) {

    ServiceHttpEndPointBeanObject resultBeanObject = new ServiceHttpEndPointBeanObject();
    ServiceHttpEndPoint srvhttpEndPoint =
        retrofitSrvInterfaceClazz.getAnnotation(ServiceHttpEndPoint.class);
    resultBeanObject.setServerType(srvhttpEndPoint.serverType());
    resultBeanObject.setServiceName(srvhttpEndPoint.serviceName());
    resultBeanObject.setServiceVersion(srvhttpEndPoint.serviceVersion());

    resultBeanObject.setClientProtocl(srvhttpEndPoint.clientProtocl());
    resultBeanObject.setMsbProtocl(srvhttpEndPoint.msbProtocl());
    if ("null".equals(srvhttpEndPoint.nameSpace())) {
      resultBeanObject.setNameSpace(null);
    } else {
      resultBeanObject.setNameSpace(srvhttpEndPoint.nameSpace());
    }

    resultBeanObject.setVisualRange(srvhttpEndPoint.visualRange());


    return resultBeanObject;
  }

}
