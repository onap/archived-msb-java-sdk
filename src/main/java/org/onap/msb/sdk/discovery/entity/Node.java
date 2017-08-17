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
package org.onap.msb.sdk.discovery.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Node implements Serializable {
  private static final long serialVersionUID = 1L;

  private String ip;

  private String port;

  private String ttl = "";

  // 负载均衡策略参数
  private String lb_server_params = "";

  // 健康检查参数
  private String checkType = "";
  private String checkUrl = "";
  private String checkInterval = "";
  private String checkTimeOut = "";

  private String ha_role = "";


  public String getHa_role() {
    return ha_role;
  }


  /**
   * @Title setHa_role
   * @Description TODO(实例主备状态，取值范围：active,standby)
   * @param ha_role
   * @return void
   */
  public void setHa_role(String ha_role) {
    this.ha_role = ha_role;
  }



  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }

  public String getPort() {
    return port;
  }

  public void setPort(String port) {
    this.port = port;
  }

  public String getTtl() {
    return ttl;
  }

  public void setTtl(String ttl) {
    this.ttl = ttl;
  }


  @Override
  public String toString() {
    // TODO Auto-generated method stub
    return (new StringBuilder().append(ip).append(":").append(port).append("  ttl:").append(ttl))
        .toString();
  }

  public String getLb_server_params() {
    return lb_server_params;
  }

  /**
   * @Title setLb_server_params
   * @Description TODO(针对协议为TCP或UDP的服务实例配置负载均衡参数) <br>
   *              支持的server参数设置： <br>
   *              1. weight: server的权重，不显式设置默认为1 <br>
   *              2.
   *              max_fails：在fail_timeout设置的时间间隔内，允许失败尝试的次数，不显式设置默认为1.比如fail_timeout=30s，max_fails=6，那表示在30s内如果6次连接失败，那认定该server为unavailable
   *              <br>
   *              3. fail_timeout：判定server为unavailable的时间间隔。如果不显式设置，默认设置为10s
   * @param lb_server_params (示例：weight=5,max_fails=3,fail_timeout=30s)
   * @return void
   */
  public void setLb_server_params(String lb_server_params) {
    this.lb_server_params = lb_server_params;
  }

  public String getCheckType() {
    return checkType;
  }

  /**
   * @Title setCheckType
   * @Description TODO(健康检查类型，可选范围：TTL,HTTP,TCP) <br>
   *              1.HTTP保活由"MSB"每隔checkInterval时间向"服务提供方"发送 GET请求。如果请求返回任何2xx状态码，检测成功。 <br>
   *              2.TCP保活原理同HTTP，协议不同。 <br>
   *              3.TTL保活由"服务提供方"向"MSB"发送HTTP请求以保持联通状态，在checkInterval设置的时间间隔内未能收到保活请求，服务发现将此服务状态设置为无效。
   * @param checkType
   * @return void
   */
  public void setCheckType(String checkType) {
    this.checkType = checkType;
  }

  public String getCheckUrl() {
    return checkUrl;
  }

  /**
   * @Title setCheckUrl
   * @Description TODO(健康检查类型为HTTP或TCP，填写检查URL)
   * @param checkUrl
   * @return void
   */
  public void setCheckUrl(String checkUrl) {
    this.checkUrl = checkUrl;
  }

  public String getCheckInterval() {
    return checkInterval;
  }

  /**
   * @Title setCheckInterval
   * @Description TODO(健康检查轮询时间，单位：秒)
   * @param checkInterval
   * @return void
   */
  public void setCheckInterval(String checkInterval) {
    this.checkInterval = checkInterval;
  }


  public String getCheckTimeOut() {
    return checkTimeOut;
  }


  public void setCheckTimeOut(String checkTimeOut) {
    this.checkTimeOut = checkTimeOut;
  }

}
