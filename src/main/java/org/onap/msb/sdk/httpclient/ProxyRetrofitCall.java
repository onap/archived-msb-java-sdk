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
package org.onap.msb.sdk.httpclient;

import java.io.IOException;
import java.lang.reflect.Method;

import org.onap.msb.sdk.httpclient.handler.RetrofitServiceHandler;
import org.onap.msb.sdk.httpclient.metric.MetricObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProxyRetrofitCall<T extends Object> implements Call<T> {

  private static final Logger logger = LoggerFactory.getLogger(ProxyRetrofitCall.class);

  private Call<T> targetCall;

  private RetrofitServiceHandler handler;

  private ServiceHttpEndPointObject endPoint;

  private Object proxy;

  private Method method;

  private Object[] args;

  private MetricObject metricObject;



  public ProxyRetrofitCall(Call targetCall, RetrofitServiceHandler retrofitServiceHandler,
      ServiceHttpEndPointObject endPoint, Object proxy, Method method, Object[] args,
      MetricObject metricObject) {
    super();
    this.targetCall = targetCall;
    this.handler = retrofitServiceHandler;
    this.endPoint = endPoint;
    this.proxy = proxy;
    this.method = method;
    this.args = args;
    this.metricObject = metricObject;
  }

  @Override
  public Response<T> execute() throws IOException {


    try {
      return targetCall.execute();
    } catch (Exception e) {

      logger.warn("first invoke httpclient error,endPoint:{},method:{},msg:{}", endPoint, method,
          e.getMessage());

      // 清理残留的endpoint记录
      handler.clean();
      try {
        return ((Call) handler.reInvoke(proxy, method, args, endPoint)).execute();
      } catch (IOException e1) {
        logger.error("sencond invoke httpclient error,endPoint:{},method:{}", endPoint, method, e1);
        throw e1;
      } catch (Throwable e2) {
        logger.error("sencond invoke httpclient error,endPoint:{},method:{}", endPoint, method, e2);
        throw e;
      }



    } finally {

    }

  }

  @Override
  public void enqueue(Callback<T> callback) {
    targetCall.enqueue(callback);
  }

  @Override
  public boolean isExecuted() {
    return targetCall.isExecuted();
  }

  @Override
  public void cancel() {
    targetCall.cancel();

  }

  @Override
  public boolean isCanceled() {
    // TODO Auto-generated method stub
    return targetCall.isCanceled();
  }

  @Override
  public Call<T> clone() {
    // TODO Auto-generated method stub
    return new ProxyRetrofitCall(targetCall.clone(), this.handler, this.endPoint, this.proxy,
        this.method, this.args, metricObject);
  }

  @Override
  public Request request() {
    return targetCall.request();
  }

}
