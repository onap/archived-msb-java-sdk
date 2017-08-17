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
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Service<T> implements Serializable {
  private static final long serialVersionUID = 1L;
  // 服务名[必填]
  private String serviceName;
  // 版本号 ( 格式：v（小写）+数字)
  private String version = "";
  // 服务url (格式：url地址以/开头，不能以/结尾)
  private String url = "";
  // 服务对应协议，比如REST、UI、HTTP、MQ、FTP、SNMP、TCP、UDP
  private String protocol = "";
  // 服务的可见范围 0:系统间 1:系统内
  private String visualRange = "1";

  // 负载均衡策略类型
  private String lb_policy = "";


  private String host = "";

  private String path = "";

  private Set<T> nodes;

  // 服务自身属性的键值对
  private List<KeyVaulePair> metadata;

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public String getLb_policy() {
    return lb_policy;
  }

  /**
   * @Title setLb_policy
   * @Description TODO(针对协议为TCP或UDP的服务选择负载均衡策略)
   * @param lb_policy (可选策略：round-robin,ip_hash)
   * @return void
   */
  public void setLb_policy(String lb_policy) {
    this.lb_policy = lb_policy;
  }

  public List<KeyVaulePair> getMetadata() {
    return metadata;
  }

  /**
   * @Title setMetadata
   * @Description TODO(配置服务特有的附加属性键值对)
   * @param metadata
   * @return void
   */
  public void setMetadata(List<KeyVaulePair> metadata) {
    this.metadata = metadata;
  }

  public Set<T> getNodes() {
    return nodes;
  }

  /**
   * @Title setNodes
   * @Description TODO(配置服务的服务器实例列表)
   * @param nodes
   * @return void
   */
  public void setNodes(Set<T> nodes) {
    this.nodes = nodes;
  }

  public String getServiceName() {
    return serviceName;
  }

  /**
   * 
   * @Title setServiceName
   * @Description TODO(服务名[必填])
   * @param serviceName
   * @return void
   */
  public void setServiceName(String serviceName) {
    this.serviceName = serviceName;
  }

  public String getVersion() {
    return version;
  }

  /**
   * 
   * @Title setVersion
   * @Description TODO(版本号 ( 格式：v（小写）+数字))
   * @param version
   * @return void
   */
  public void setVersion(String version) {
    this.version = version;
  }

  public String getUrl() {
    return url;
  }

  /**
   * @Title setUrl
   * @Description TODO(目标服务URL地址 (格式：url地址以/开头，不能以/结尾))
   * @param url
   * @return void
   */
  public void setUrl(String url) {
    this.url = url;
  }

  public String getProtocol() {
    return protocol;
  }

  /**
   * @Title setProtocol
   * @Description TODO(服务对应协议，比如REST、MQ、FTP、SNMP[必填])
   * @param protocol
   * @return void
   */
  public void setProtocol(String protocol) {
    this.protocol = protocol;
  }


  public String getVisualRange() {
    return visualRange;
  }

  /**
   * @Title setVisualRange
   * @Description TODO(服务的可见范围 系统间:0 系统内:1 ，多个可见范围用 "|"分隔，如"0|1" )
   * @param visualRange
   * @return void
   */
  public void setVisualRange(String visualRange) {
    this.visualRange = visualRange;
  }

}
