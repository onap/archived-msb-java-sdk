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
public class MicroServiceFullInfo extends Service<NodeInfo> implements Serializable {
    private static final long serialVersionUID = 1L;

    // Reserved
    private String status = "1";

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String toString() {
        StringBuffer buf = new StringBuffer();
        buf.append("MicroService List:").append("\r\n");;
        buf.append("serviceName:").append(getServiceName()).append("\r\n");
        buf.append("version:").append(getVersion()).append("\r\n");
        buf.append("url:").append(getUrl()).append("\r\n");
        buf.append("protocol:").append(getProtocol()).append("\r\n");
        buf.append("visualRange:").append(getVisualRange()).append("\r\n");
        buf.append("nodes:").append("\r\n");

        for (NodeInfo nodeInstace : this.getNodes()) {
            buf.append("  nodeId-").append(nodeInstace.getNodeId()).append("\r\n");
            buf.append("  ip-").append(nodeInstace.getIp()).append("\r\n");
            buf.append("  port-").append(nodeInstace.getPort()).append("\r\n");
            buf.append("  ttl-").append(nodeInstace.getTtl()).append("\r\n");
            buf.append("  Created_at-").append(nodeInstace.getCreated_at()).append("\r\n");
            buf.append("  Updated_at-").append(nodeInstace.getUpdated_at()).append("\r\n");
            buf.append("  Expiration-").append(nodeInstace.getExpiration()).append("\r\n");
        }
        buf.append("metadata:").append("\r\n");

        if (this.getMetadata() != null && this.getMetadata().size() > 0) {
            for (KeyVaulePair keyVaulePair : this.getMetadata()) {
                buf.append("  key-").append(keyVaulePair.getKey()).append("\r");
                buf.append("  value-").append(keyVaulePair.getValue()).append("\r\n");

            }
        }
        return buf.toString();
    }

}
