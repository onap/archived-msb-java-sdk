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

import org.onap.msb.sdk.httpclient.ServiceHttpEndPointBeanObject;
import org.onap.msb.sdk.httpclient.annotaion.LoadBalance.LBSTYLE;
import org.onap.msb.sdk.httpclient.builder.IRetrofitObjectBuilder;
import org.onap.msb.sdk.httpclient.conf.HttpClientConf;
import org.onap.msb.sdk.httpclient.convert.IConverterFactoryBuilder;
import org.onap.msb.sdk.httpclient.lb.ILoadBalanceStrategy;
import org.onap.msb.sdk.httpclient.msb.MSBServiceClient;


/**
 * @author 10071214
 *
 */
public class RetrofitServiceHandlerContext {

  private static HttpClientConf globalHttpClientConf;



  public static HttpClientConf getGlobalHttpClientConf() {
    return globalHttpClientConf;
  }

  public static void setGlobalHttpClientConf(HttpClientConf globalHttpClientConf) {
    RetrofitServiceHandlerContext.globalHttpClientConf = globalHttpClientConf;
  }

  private Class<?> retrofitSrvInterfaceClazz;


  private LBSTYLE lbStyle;

  private IConverterFactoryBuilder converterFactoryBuilder;


  private ILoadBalanceStrategy lbStrategy;


  private IRetrofitObjectBuilder retrofitObjectBuilder;

  private ServiceHttpEndPointBeanObject serviceHttpEndPointBeanObject = null;

  private long lastUpdateMsbTime;

  private HttpClientConf httpClientConf;

  private MSBServiceClient msbClient;



  public Class<?> getRetrofitSrvInterfaceClazz() {
    return retrofitSrvInterfaceClazz;
  }

  public void setRetrofitSrvInterfaceClazz(Class<?> retrofitSrvInterfaceClazz) {
    this.retrofitSrvInterfaceClazz = retrofitSrvInterfaceClazz;
  }



  public MSBServiceClient getMsbClient() {
    return msbClient;
  }

  public void setMsbClient(MSBServiceClient msbClient) {
    this.msbClient = msbClient;
  }

  public LBSTYLE getLbStyle() {
    return lbStyle;
  }

  public void setLbStyle(LBSTYLE lbStyle) {
    this.lbStyle = lbStyle;
  }

  public IConverterFactoryBuilder getConverterFactoryBuilder() {
    return converterFactoryBuilder;
  }

  public void setConverterFactoryBuilder(IConverterFactoryBuilder converterFactoryBuilder) {
    this.converterFactoryBuilder = converterFactoryBuilder;
  }


  public ILoadBalanceStrategy getLbStrategy() {
    return lbStrategy;
  }

  public void setLbStrategy(ILoadBalanceStrategy lbStrategy) {
    this.lbStrategy = lbStrategy;
  }


  public IRetrofitObjectBuilder getRetrofitObjectBuilder() {
    return retrofitObjectBuilder;
  }

  public void setRetrofitObjectBuilder(IRetrofitObjectBuilder retrofitObjectBuilder) {
    this.retrofitObjectBuilder = retrofitObjectBuilder;
  }

  public ServiceHttpEndPointBeanObject getServiceHttpEndPointBeanObject() {
    return serviceHttpEndPointBeanObject;
  }

  public void setServiceHttpEndPointBeanObject(
      ServiceHttpEndPointBeanObject serviceHttpEndPointBeanObject) {
    this.serviceHttpEndPointBeanObject = serviceHttpEndPointBeanObject;
  }

  public long getLastUpdateMsbTime() {
    return lastUpdateMsbTime;
  }

  public void setLastUpdateMsbTime(long lastUpdateMsbTime) {
    this.lastUpdateMsbTime = lastUpdateMsbTime;
  }

  public HttpClientConf getHttpClientConf() {
    return httpClientConf;
  }

  public void setHttpClientConf(HttpClientConf httpClientConf) {
    this.httpClientConf = httpClientConf;
  }



}
