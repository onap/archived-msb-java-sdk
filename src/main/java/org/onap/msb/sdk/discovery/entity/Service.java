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
 
  private String serviceName;

  private String version = "";
  
  private String url = "";
  
  //REST、UI
  private String protocol = "";
  //0: External 1: Internal
  private String visualRange = "1";

  private String lb_policy = "";

  private String path = "";

  private Set<T> nodes;

  private List<KeyVaulePair> metadata;

  public String getPath() {
    return path;
  }

  /**
   * The customized publish path of this service.
   * If this parameter is specified when registering the service, the service will be published to api gateway under this path.
   * Otherwise, the service will be published to api gateway using a fixed format: api/{serviceName} /{version}.
   * Do not specific a path unless you know what you're doing.
   * @param path
   */
  public void setPath(String path) {
    this.path = path;
  }

  /**
   * Load balancing method used when MSB routes the service requests. Currently, Round robin and IP hash are supported.
   * @return
   */
  public String getLb_policy() {
    return lb_policy;
  }

  /**
   * Load balancing method used when MSB routes the service requests. Currently, Round robin and IP hash are supported.
   * @param lb_policy (round-robin,ip_hash)
   * @return void
   */
  public void setLb_policy(String lb_policy) {
    this.lb_policy = lb_policy;
  }

  public List<KeyVaulePair> getMetadata() {
    return metadata;
  }

  public void setMetadata(List<KeyVaulePair> metadata) {
    this.metadata = metadata;
  }

  /**
   * The service instance nodes
   */
  public Set<T> getNodes() {
    return nodes;
  }

  /**
   * The service instance nodes
   * @param nodes
   * @return void
   */
  public void setNodes(Set<T> nodes) {
    this.nodes = nodes;
  }

  /**
   * An unique name of the service, it should be constant so the service consumer can access the service.
   */
  public String getServiceName() {
    return serviceName;
  }

  /**
   * An unique name of the service, it should be constant so the service consumer can access the service.
   * @param serviceName
   */
  public void setServiceName(String serviceName) {
    this.serviceName = serviceName;
  }

  /**
   * Service version. Only the major version of service is used in the URI.
   * @return
   */
  public String getVersion() {
    return version;
  }

  /**
   * Service version. Only the major version of service is used in the URI.
   * @param version
   */
  public void setVersion(String version) {
    this.version = version;
  }

  /**
   * The actual URL of the service to be registered.
   * @return
   */
  public String getUrl() {
    return url;
  }

  /**
   * The actual URL of the service to be registered.
   * @param url
   * @return void
   */
  public void setUrl(String url) {
    this.url = url;
  }

  /**
   * supported protocols: 'REST', 'UI'
   * @return
   */
  public String getProtocol() {
    return protocol;
  }

  /**
   * supported protocols: 'REST', 'UI'
   * @param protocol
   */
  public void setProtocol(String protocol) {
    this.protocol = protocol;
  }

  /**
   * Visibility of the service. 
   * External(can be accessed by external systems):0
   * Internal(can only be accessed by ONAP microservices):1
   */
  public String getVisualRange() {
    return visualRange;
  }

  /**
   * Visibility of the service. 
   * External(can be accessed by external systems):0
   * Internal(can only be accessed by ONAP microservices):1
   * @param visualRange
   */
  public void setVisualRange(String visualRange) {
    this.visualRange = visualRange;
  }

}
