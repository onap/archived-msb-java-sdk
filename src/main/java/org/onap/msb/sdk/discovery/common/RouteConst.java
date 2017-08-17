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
package org.onap.msb.sdk.discovery.common;

public class RouteConst {
  public static final int SC_OK = 200;
  public static final int SC_POST_OK = 201;
  public static final int SC_DEL_OK = 204;
  public static final int SC_NOTFOND = 404;
  public static final String MSB_ROUTE_URL = "/api/microservices/v1/services";
  public static final String REQUEST_SUCCESS = "SUCCESS";
  public static final String REQUEST_FAIL = "FAIL";

  public static final String LB_POLICY_LIST = "round-robin,ip_hash,least_conn";
  public static final String LB_PARAMS_LIST = "weight,max_fails,fail_timeout";
  public static final String SERVER_TYPE_LIST = "api,iui,custom,p2p";
  public static final String CHECK_TYPE_LIST = "HTTP,TCP,TTL";
  public static final String APIGATEWAY_NAME = "apigateway";
  public static final String VISUAL_RANGE_IN = "1";



  public enum Protocol {
    REST, HTTP, MQ, FTP, SNMP, TCP, UDP, UI
  }

  public static boolean checkExistProtocol(String value) {
    for (Protocol protocol : Protocol.values()) {
      if (protocol.name().equals(value)) {
        return true;
      }
    }
    return false;
  }


  public static boolean checkExist(String list, String value) {
    String[] listArray = list.split(",");
    for (int i = 0; i < listArray.length; i++) {
      if (value.equals(listArray[i]))
        return true;
    }
    return false;
  }

  public static String listProtocol() {
    String list = "";
    for (Protocol protocol : Protocol.values()) {
      list += protocol.name() + " | ";
    }
    return list;
  }

}
