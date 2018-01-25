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
package org.onap.msb.sdk.discovery.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.onap.msb.sdk.discovery.common.RouteConst;
import org.onap.msb.sdk.discovery.common.RouteException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpClientUtil {

  private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

  public static String httpPostWithJSON(String url, String params) throws RouteException {
    String result = null;
    CloseableHttpClient httpClient = HttpClients.createDefault();
    HttpPost httpPost = new HttpPost(url);
    httpPost.addHeader("Content-type", "application/json; charset=utf-8");
    httpPost.setHeader("Accept", "application/json");
    httpPost.setEntity(new StringEntity(params, Charset.forName("UTF-8")));
    try {
      CloseableHttpResponse res = httpClient.execute(httpPost);
      result = EntityUtils.toString(res.getEntity());
      if (res.getStatusLine().getStatusCode() != RouteConst.SC_POST_OK) {
        throw new RouteException(result, "SERVICE_GET_ERR");
      }
      res.close();
    } catch (IOException e) {
      throwsRouteException(url + ":httpPostWithJSON connect faild", e, "POST_CONNECT_FAILD");
    } finally {
      try {
        httpClient.close();
      } catch (IOException e) {
        throwsRouteException(url + ":close  httpClient faild", e, "CLOSE_CONNECT_FAILD");
      }
    }

    return result;

  }

  public static void delete(String url, String parameter) throws RouteException {
    String result = null;
    String baseUrl;
    if (parameter != null) {
      List<NameValuePair> params = new ArrayList<NameValuePair>();
      params.add(new BasicNameValuePair("serviceName", parameter));
      baseUrl = url + "?" + URLEncodedUtils.format(params, "UTF-8");
    } else {
      baseUrl = url;
    }

    CloseableHttpClient httpClient = HttpClients.createDefault();;
    try {

      HttpDelete httpDelete = new HttpDelete(baseUrl);
      CloseableHttpResponse res = httpClient.execute(httpDelete);

      if (res.getStatusLine().getStatusCode() != RouteConst.SC_DEL_OK) {
        throw new RouteException(EntityUtils.toString(res.getEntity()), "SERVICE_DELETE_ERR");
      }

      res.close();
    } catch (IOException e) {
      throwsRouteException(baseUrl + ":delete connect faild", e, "DELETE_CONNECT_FAILD");
    } finally {
      try {
        httpClient.close();
      } catch (IOException e) {
        throwsRouteException(baseUrl + ":close  httpClient faild", e, "CLOSE_CONNECT_FAILD");
      }
    }


  }

  public static String httpGet(String url) throws RouteException {
    String result = null;
    CloseableHttpClient httpClient = HttpClients.createDefault();
    HttpGet httpGet = new HttpGet(url);
    httpGet.addHeader("Content-type", "application/json; charset=utf-8");
    httpGet.setHeader("Accept", "application/json");
    try {
      CloseableHttpResponse res = httpClient.execute(httpGet);
      result = EntityUtils.toString(res.getEntity());
      if (res.getStatusLine().getStatusCode() != RouteConst.SC_OK) {
        throw new RouteException(result, "SERVICE_GET_ERR");
      }
      res.close();
    } catch (ClientProtocolException e) {
      throwsRouteException(url + ":httpGetWithJSON connect faild", e, "GET_CONNECT_FAILD");
    } catch (IOException e) {
      throwsRouteException(url + ":httpGetWithJSON connect faild", e, "GET_CONNECT_FAILD");
    } finally {
      try {
        httpClient.close();
      } catch (IOException e) {
        throwsRouteException(url + ":close  httpClient faild", e, "CLOSE_CONNECT_FAILD");
      }
    }

    return result;

  }


  public static String httpPutWithJSON(String url, String params) throws RouteException {
    String result = null;
    CloseableHttpClient httpClient = HttpClients.createDefault();
    HttpPut httpPut = new HttpPut(url);
    httpPut.addHeader("Content-type", "application/json; charset=utf-8");
    httpPut.setHeader("Accept", "application/json");
    httpPut.setEntity(new StringEntity(params, Charset.forName("UTF-8")));
    try {
      CloseableHttpResponse res = httpClient.execute(httpPut);
      result = EntityUtils.toString(res.getEntity());
      if (res.getStatusLine().getStatusCode() != RouteConst.SC_POST_OK) {
        throw new RouteException(result, "SERVICE_GET_ERR");
      }
      res.close();
    } catch (IOException e) {
      String errorMsg = url + ":httpPostWithJSON connect faild";
      throwsRouteException(errorMsg, e, "POST_CONNECT_FAILD");
    } finally {
      try {
        httpClient.close();
      } catch (IOException e) {
        String errorMsg = url + ":close  httpClient faild";
        throwsRouteException(errorMsg, e, "CLOSE_CONNECT_FAILD");
      }
    }

    return result;

  }



  private static void throwsRouteException(String errorMsg, Exception e, String errorCode)
      throws RouteException {
    String msg = errorMsg + ".errorMsg:" + e.getMessage();
    logger.error(msg);
    throw new RouteException(errorMsg, errorCode);
  }

}
