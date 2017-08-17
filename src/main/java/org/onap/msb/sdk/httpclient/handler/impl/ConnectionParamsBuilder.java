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

import org.onap.msb.sdk.httpclient.annotaion.ConnectionParams;
import org.onap.msb.sdk.httpclient.conf.HttpClientConf;
import org.onap.msb.sdk.httpclient.exception.RetrofitServiceRuntimeException;
import org.onap.msb.sdk.httpclient.handler.HandlerContextBuilder;
import org.onap.msb.sdk.httpclient.handler.RetrofitServiceHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 10071214
 *
 */
public class ConnectionParamsBuilder implements HandlerContextBuilder {



  private final static Logger logger = LoggerFactory.getLogger(ConnectionParamsBuilder.class);

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.zte.ums.zenap.httpclient.retrofit.handler.HandlerContextBuilder#build(com.zte.ums.zenap.
   * httpclient.retrofit.handler.RetrofitServiceHandlerContext)
   */
  @Override
  public void build(RetrofitServiceHandlerContext ctx) throws RetrofitServiceRuntimeException {


    Class<?> retrofitSrvInterfaceClazz = ctx.getRetrofitSrvInterfaceClazz();

    ConnectionParams connectionParams =
        retrofitSrvInterfaceClazz.getAnnotation(ConnectionParams.class);
    if (connectionParams != null && ctx.getHttpClientConf() == null) {
      HttpClientConf httpClientConf = new HttpClientConf();
      httpClientConf.setConnectTimeout(connectionParams.connectTimeout());
      httpClientConf.setReadTimeout(connectionParams.readTimeout());
      httpClientConf.setWriteTimeout(connectionParams.writeTimeout());
      ctx.setHttpClientConf(httpClientConf);
    }


  }

}
