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
package org.onap.msb.sdk.httpclient.client;

import java.io.IOException;

import org.onap.msb.sdk.httpclient.RetrofitServiceCreater;
import org.onap.msb.sdk.httpclient.common.Animal;
import org.onap.msb.sdk.httpclient.conf.HttpClientConf;
import org.onap.msb.sdk.httpclient.msb.MSBServiceWrapperClient;


public class ExampleClient {

  /**
   * @param args
   * @throws IOException
   */
  public static void main(String[] args) throws IOException {

    HttpClientConf globalHttpClientConf = new HttpClientConf();
    MSBServiceWrapperClient msbClient = new MSBServiceWrapperClient("127.0.0.1", 10081);

    RetrofitServiceCreater retrofitServiceCreater =
        new RetrofitServiceCreater(globalHttpClientConf, msbClient);

    AnimalServiceClient implProxy =
        retrofitServiceCreater.createRetrofitService(AnimalServiceClient.class);
    Animal animal = implProxy.queryAnimal("example").execute().body();
    System.out.println("animal:" + animal);
  }

}
