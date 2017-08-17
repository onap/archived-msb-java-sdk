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
import java.util.List;

import org.onap.msb.sdk.httpclient.ServiceHttpEndPointBeanObject;
import org.onap.msb.sdk.httpclient.conf.HttpClientConf;
import org.onap.msb.sdk.httpclient.exception.RetrofitServiceRuntimeException;
import org.onap.msb.sdk.httpclient.handler.impl.ConnectionParamsBuilder;
import org.onap.msb.sdk.httpclient.handler.impl.ConverterFactoryBuilder;
import org.onap.msb.sdk.httpclient.handler.impl.LBBuilder;
import org.onap.msb.sdk.httpclient.handler.impl.MetricmanagerBuilder;
import org.onap.msb.sdk.httpclient.handler.impl.RetrofitHandlerContextBuilder;
import org.onap.msb.sdk.httpclient.handler.impl.ServiceHttpEndPointBeanObjectBuilder;
import org.onap.msb.sdk.httpclient.msb.MSBServiceWrapperClient;

import com.google.common.collect.Lists;

/**
 * @author 10071214
 *
 */

public class RetrofitServiceHandlerFactory {

  private List<HandlerContextBuilder> builders = Lists.newArrayList();

  public void init() {

    builders.add(new ServiceHttpEndPointBeanObjectBuilder());
    builders.add(new MetricmanagerBuilder());
    builders.add(new LBBuilder());
    builders.add(new ConnectionParamsBuilder());
    builders.add(new ConverterFactoryBuilder());
    builders.add(new RetrofitHandlerContextBuilder());


  }



  public InvocationHandler buildInvocationHandler(Class<?> retrofitSrvInterfaceClazz,
      ServiceHttpEndPointBeanObject serviceHttpEndPointBeanObject)
      throws RetrofitServiceRuntimeException {

    RetrofitServiceHandlerContext ctx = new RetrofitServiceHandlerContext();
    ctx.setRetrofitSrvInterfaceClazz(retrofitSrvInterfaceClazz);
    /* ctx.setLocator(locator); */
    ctx.setServiceHttpEndPointBeanObject(serviceHttpEndPointBeanObject);

    for (HandlerContextBuilder builder : builders) {
      builder.build(ctx);
    }
    return new RetrofitServiceHandler(ctx);
  }


  public InvocationHandler buildInvocationHandler(Class<?> retrofitSrvInterfaceClazz)
      throws RetrofitServiceRuntimeException {

    RetrofitServiceHandlerContext ctx = new RetrofitServiceHandlerContext();
    ctx.setRetrofitSrvInterfaceClazz(retrofitSrvInterfaceClazz);
    // ctx.setLocator(locator);

    for (HandlerContextBuilder builder : builders) {
      builder.build(ctx);
    }
    return new RetrofitServiceHandler(ctx);

  }



  public InvocationHandler buildInvocationHandler(Class<?> retrofitSrvInterfaceClazz,
      ServiceHttpEndPointBeanObject serviceHttpEndPointBeanObject, HttpClientConf httpClientConf,
      MSBServiceWrapperClient msbClient) {

    RetrofitServiceHandlerContext ctx = new RetrofitServiceHandlerContext();
    ctx.setRetrofitSrvInterfaceClazz(retrofitSrvInterfaceClazz);
    // ctx.setLocator(locator);
    ctx.setServiceHttpEndPointBeanObject(serviceHttpEndPointBeanObject);

    ctx.setHttpClientConf(httpClientConf);
    ctx.setMsbClient(msbClient);

    for (HandlerContextBuilder builder : builders) {
      builder.build(ctx);
    }
    return new RetrofitServiceHandler(ctx);

  }


}
