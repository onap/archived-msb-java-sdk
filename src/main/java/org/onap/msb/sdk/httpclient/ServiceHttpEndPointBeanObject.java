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

public class ServiceHttpEndPointBeanObject {

  // 在MSB上注册的服务名
  private String serviceName;
  // 在MSB注册的版本号
  private String serviceVersion;
  // 在通过msb转发时，所用的协议
  private String msbProtocl = "https";

  // 服务间点对点访问时，所用的协议
  private String clientProtocl = "http";

  // 服务所在的租户名
  private String nameSpace = "";

  // 服务的可见范围，系统间:“0”，系统内:“1”（默认）,可配置多个，以 | 分隔
  private String visualRange = "0";

  // 在MSB上注册的服务类型
  private String serverType = "api";


  public String getServiceName() {
    return serviceName;
  }

  public void setServiceName(String serviceName) {
    this.serviceName = serviceName;
  }

  public String getServiceVersion() {
    return serviceVersion;
  }

  public void setServiceVersion(String serviceVersion) {
    this.serviceVersion = serviceVersion;
  }


  public String getServerType() {
    return serverType;
  }

  public void setServerType(String serverType) {
    this.serverType = serverType;
  }



  public String getMsbProtocl() {
    return msbProtocl;
  }

  public void setMsbProtocl(String msbProtocl) {
    this.msbProtocl = msbProtocl;
  }

  public String getNameSpace() {
    return nameSpace;
  }

  public void setNameSpace(String nameSpace) {
    this.nameSpace = nameSpace;
  }

  public String getVisualRange() {
    return visualRange;
  }

  public void setVisualRange(String visualRange) {
    this.visualRange = visualRange;
  }

  public String getClientProtocl() {
    return clientProtocl;
  }

  public void setClientProtocl(String clientProtocl) {
    this.clientProtocl = clientProtocl;
  }

  @Override
  public String toString() {
    return "ServiceHttpEndPointBeanObject [serviceName=" + serviceName + ", serviceVersion="
        + serviceVersion + ", msbProtocl=" + msbProtocl + ", clientProtocl=" + clientProtocl
        + ", nameSpace=" + nameSpace + ", visualRange=" + visualRange + ", serverType=" + serverType
        + "]";
  }



}
