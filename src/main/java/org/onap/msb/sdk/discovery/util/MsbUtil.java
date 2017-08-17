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

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.onap.msb.sdk.discovery.entity.Node;
import org.onap.msb.sdk.discovery.entity.NodeInfo;

/**
 * @ClassName: MsbUtil
 * @Description: TODO(msb功能工具方法类)
 * @author tanghua10186366
 * @date 2017年6月26日
 * 
 */
public class MsbUtil {


  /**
   * @Title getConsulServiceName
   * @Description TODO(通过服务名和命名空间组装conusl存储名，用于服务变化监听)
   * @param serviceName
   * @param namespace
   * @return String
   */
  public static String getConsulServiceName(String serviceName, String namespace) {
    if (StringUtils.isEmpty(namespace)) {
      return serviceName;
    } else {
      return serviceName + "-" + namespace;
    }
  }

  public static Set<NodeInfo> getLbNodes(Node lbNode) {
    Set<NodeInfo> nodes = new HashSet<NodeInfo>();
    NodeInfo nodeInfo = new NodeInfo();
    nodeInfo.setIp(lbNode.getIp());
    nodeInfo.setPort(lbNode.getPort());
    nodes.add(nodeInfo);

    return nodes;
  }

}
