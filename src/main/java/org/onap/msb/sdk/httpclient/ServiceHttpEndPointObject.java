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

import org.onap.msb.sdk.discovery.entity.MicroServiceFullInfo;
import org.onap.msb.sdk.discovery.entity.NodeInfo;

/**
 * @author hu.rui
 *
 */
public class ServiceHttpEndPointObject {


  private String serviceName;

  private String serviceVersion;

  private String ip;

  private String port;

  private String protocl = "http";

  private String nameSpace = "";

  private NodeInfo nodeInfo;

  private MicroServiceFullInfo cloneFullInfo;


  public ServiceHttpEndPointObject(String serviceName, String serviceVersion, NodeInfo nodeInfo,
      MicroServiceFullInfo cloneFullInfo) {

    this.serviceName = serviceName;
    this.serviceVersion = serviceVersion;
    this.nodeInfo = nodeInfo;
    this.cloneFullInfo = cloneFullInfo;

    this.ip = nodeInfo.getIp();
    this.port = nodeInfo.getPort();

  }



  public String getServiceName() {
    return serviceName;
  }



  public String getServiceVersion() {
    return serviceVersion;
  }



  public String getIp() {
    return ip;
  }



  public String getPort() {
    return port;
  }



  public NodeInfo getNodeInfo() {
    return nodeInfo;
  }



  public MicroServiceFullInfo getCloneFullInfo() {
    return cloneFullInfo;
  }



  public String getProtocl() {
    return protocl;
  }



  public void setProtocl(String protocl) {
    this.protocl = protocl;
  }



  public String getNameSpace() {
    return nameSpace;
  }



  public void setNameSpace(String nameSpace) {
    this.nameSpace = nameSpace;
  }



  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((ip == null) ? 0 : ip.hashCode());
    result = prime * result + ((nameSpace == null) ? 0 : nameSpace.hashCode());
    result = prime * result + ((port == null) ? 0 : port.hashCode());
    result = prime * result + ((protocl == null) ? 0 : protocl.hashCode());
    result = prime * result + ((serviceName == null) ? 0 : serviceName.hashCode());
    result = prime * result + ((serviceVersion == null) ? 0 : serviceVersion.hashCode());
    return result;
  }



  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ServiceHttpEndPointObject other = (ServiceHttpEndPointObject) obj;
    if (ip == null) {
      if (other.ip != null)
        return false;
    } else if (!ip.equals(other.ip))
      return false;
    if (nameSpace == null) {
      if (other.nameSpace != null)
        return false;
    } else if (!nameSpace.equals(other.nameSpace))
      return false;
    if (port == null) {
      if (other.port != null)
        return false;
    } else if (!port.equals(other.port))
      return false;
    if (protocl == null) {
      if (other.protocl != null)
        return false;
    } else if (!protocl.equals(other.protocl))
      return false;
    if (serviceName == null) {
      if (other.serviceName != null)
        return false;
    } else if (!serviceName.equals(other.serviceName))
      return false;
    if (serviceVersion == null) {
      if (other.serviceVersion != null)
        return false;
    } else if (!serviceVersion.equals(other.serviceVersion))
      return false;
    return true;
  }



  @Override
  public String toString() {
    return "ServiceHttpEndPointObject [serviceName=" + serviceName + ", serviceVersion="
        + serviceVersion + ", ip=" + ip + ", port=" + port + ", protocl=" + protocl + ", nameSpace="
        + nameSpace + ", nodeInfo=" + nodeInfo + ", cloneFullInfo=" + cloneFullInfo + "]";
  }



}
