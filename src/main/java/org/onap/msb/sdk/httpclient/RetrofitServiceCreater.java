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
package org.onap.msb.sdk.httpclient;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

import org.onap.msb.sdk.httpclient.conf.HttpClientConf;
import org.onap.msb.sdk.httpclient.handler.RetrofitServiceHandlerContext;
import org.onap.msb.sdk.httpclient.handler.RetrofitServiceHandlerFactory;
import org.onap.msb.sdk.httpclient.msb.MSBServiceClient;

public class RetrofitServiceCreater {


  private RetrofitServiceHandlerFactory factory = new RetrofitServiceHandlerFactory();

  private MSBServiceClient msbClient;


  public RetrofitServiceCreater(HttpClientConf globalHttpClientConf,
      MSBServiceClient msbClient) {

    RetrofitServiceHandlerContext.setGlobalHttpClientConf(globalHttpClientConf);
    this.msbClient = msbClient;

    factory.init();


  }

  public <T> T createRetrofitService(Class<T> retrofitSrvInterfaceClazz,
      ServiceHttpEndPointBeanObject serviceHttpEndPointBeanObject) {

    InvocationHandler handler = factory.buildInvocationHandler(retrofitSrvInterfaceClazz,
        serviceHttpEndPointBeanObject, null, msbClient);

    List<Class<?>> clazzList = new ArrayList<>();
    clazzList.add(retrofitSrvInterfaceClazz);

    Object targetInterface = Proxy.newProxyInstance(retrofitSrvInterfaceClazz.getClassLoader(),
        clazzList.toArray(new Class[] {}), handler);

    return (T) targetInterface;
  }


  public <T> T createRetrofitService(Class<T> retrofitSrvInterfaceClazz,
      ServiceHttpEndPointBeanObject serviceHttpEndPointBeanObject, HttpClientConf httpClientConf) {


    InvocationHandler handler = factory.buildInvocationHandler(retrofitSrvInterfaceClazz,
        serviceHttpEndPointBeanObject, httpClientConf, msbClient);

    List<Class<?>> clazzList = new ArrayList<>();
    clazzList.add(retrofitSrvInterfaceClazz);

    Object targetInterface = Proxy.newProxyInstance(retrofitSrvInterfaceClazz.getClassLoader(),
        clazzList.toArray(new Class[] {}), handler);

    return (T) targetInterface;
  }

  public <T> T createRetrofitService(Class<T> retrofitSrvInterfaceClazz,
      HttpClientConf httpClientConf) {


    InvocationHandler handler =
        factory.buildInvocationHandler(retrofitSrvInterfaceClazz, null, httpClientConf, msbClient);

    List<Class<?>> clazzList = new ArrayList<>();
    clazzList.add(retrofitSrvInterfaceClazz);

    Object targetInterface = Proxy.newProxyInstance(retrofitSrvInterfaceClazz.getClassLoader(),
        clazzList.toArray(new Class[] {}), handler);

    return (T) targetInterface;
  }


  public <T> T createRetrofitService(Class<T> retrofitSrvInterfaceClazz) {


    InvocationHandler handler =
        factory.buildInvocationHandler(retrofitSrvInterfaceClazz, null, null, msbClient);

    List<Class<?>> clazzList = new ArrayList<>();
    clazzList.add(retrofitSrvInterfaceClazz);

    Object targetInterface = Proxy.newProxyInstance(retrofitSrvInterfaceClazz.getClassLoader(),
        clazzList.toArray(new Class[] {}), handler);

    return (T) targetInterface;
  }


}
