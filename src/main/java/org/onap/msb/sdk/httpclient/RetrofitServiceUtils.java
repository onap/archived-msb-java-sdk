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

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.onap.msb.sdk.httpclient.conf.HttpClientConf;

import okhttp3.OkHttpClient;

/**
 * @author 10071214
 *
 */
public class RetrofitServiceUtils {



  private static Map<HttpClientConf, OkHttpClient> httpMap = new HashMap<>();

  private static Map<HttpClientConf, OkHttpClient> httpsMap = new HashMap<>();

  public synchronized static OkHttpClient buildDefaultOkHttpsClient(HttpClientConf httpClientConf)
      throws Exception {

    if (!httpsMap.containsKey(httpClientConf)) {
      OkHttpClient httpsOkHttpClient = buildOkHttpsClient(httpClientConf);
      httpsMap.put(httpClientConf, httpsOkHttpClient);
    }
    return httpsMap.get(httpClientConf);
  }


  public synchronized static OkHttpClient buildDefaultOkHttpClient(HttpClientConf httpClientConf)
      throws Exception {

    if (!httpMap.containsKey(httpClientConf)) {
      OkHttpClient httpOkHttpClient = buildOkHttpClient(httpClientConf);
      httpMap.put(httpClientConf, httpOkHttpClient);
    }

    return httpMap.get(httpClientConf);
  }



  private static OkHttpClient buildOkHttpsClient(HttpClientConf httpClientConf) throws Exception {

    if (httpClientConf == null) {
      httpClientConf = new HttpClientConf();
    }

    TrustManager[] trustManager = new TrustManager[] {new X509TrustManager() {
      @Override
      public void checkClientTrusted(X509Certificate[] chain, String authType)
          throws CertificateException {}

      @Override
      public void checkServerTrusted(X509Certificate[] chain, String authType)
          throws CertificateException {}

      @Override
      public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[0];
      }
    }};

    SSLContext sslContext = SSLContext.getInstance("SSL");
    sslContext.init(null, trustManager, new SecureRandom());
    SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();



    return new OkHttpClient.Builder()
        .connectTimeout(httpClientConf.getConnectTimeout(), TimeUnit.MILLISECONDS)
        .readTimeout(httpClientConf.getReadTimeout(), TimeUnit.MILLISECONDS)
        .writeTimeout(httpClientConf.getWriteTimeout(), TimeUnit.MILLISECONDS)
        .sslSocketFactory(sslSocketFactory).hostnameVerifier(new HostnameVerifier() {
          @Override
          public boolean verify(String hostname, SSLSession session) {
            return true;
          }
        }).build();
  }


  private static OkHttpClient buildOkHttpClient(HttpClientConf httpClientConf) throws Exception {

    if (httpClientConf == null) {
      httpClientConf = new HttpClientConf();
    }

    return new OkHttpClient.Builder()
        .connectTimeout(httpClientConf.getConnectTimeout(), TimeUnit.MILLISECONDS)
        .readTimeout(httpClientConf.getReadTimeout(), TimeUnit.MILLISECONDS)
        .writeTimeout(httpClientConf.getWriteTimeout(), TimeUnit.MILLISECONDS).build();
  }

}
