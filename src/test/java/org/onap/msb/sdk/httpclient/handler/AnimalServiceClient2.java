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
package org.onap.msb.sdk.httpclient.handler;

import org.onap.msb.sdk.httpclient.annotaion.BodyConverter;
import org.onap.msb.sdk.httpclient.annotaion.ConnectionParams;
import org.onap.msb.sdk.httpclient.annotaion.LoadBalance;
import org.onap.msb.sdk.httpclient.annotaion.LoadBalance.LBSTYLE;
import org.onap.msb.sdk.httpclient.annotaion.ServiceHttpEndPoint;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

@ServiceHttpEndPoint(serviceName = "animals", serviceVersion = "v1")
@LoadBalance(lbStyle=LBSTYLE.MSB,lbClassName="org.onap.msb.sdk.httpclient.lb.RoundRobinLBStrategy")
@BodyConverter(builderClassName="org.onap.msb.sdk.httpclient.convert.jackson.JacksonConverterFactoryBuilder")
@ConnectionParams(readTimeout=1000,connectTimeout=2000,writeTimeout=3000)
public interface AnimalServiceClient2 {

  @GET("animals/{name}")
  
  Call<String> queryAnimal(@Path("name") String name);
}
