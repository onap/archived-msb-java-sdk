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

/**
 * @ClassName: MicroServiceInfo Bean
 * @Description
 * @author 10188044
 * @date 2016-1-19
 */
public class MicroServiceInfo extends Service<Node> implements Serializable {
    private static final long serialVersionUID = 1L;

    public String toString() {
    	StringBuilder buf = new StringBuilder();
        buf.append("MicroService List:").append("\r\n");;
        buf.append("serviceName:").append(getServiceName()).append("\r\n");
        buf.append("version:").append(getVersion()).append("\r\n");
        buf.append("url:").append(getUrl()).append("\r\n");
        buf.append("protocol:").append(getProtocol()).append("\r\n");
        buf.append("visualRange:").append(getVisualRange()).append("\r\n");
        buf.append("enable_ssl:").append(isEnable_ssl()).append("\r\n");
        buf.append("nodes:").append("\r\n");

        for (Node nodeInstace : this.getNodes()) {
            buf.append("  ip-").append(nodeInstace.getIp()).append("\r\n");
            buf.append("  port-").append(nodeInstace.getPort()).append("\r\n");
            buf.append("  ttl-").append(nodeInstace.getTtl()).append("\r\n");
        }
        return buf.toString();
    }
}
