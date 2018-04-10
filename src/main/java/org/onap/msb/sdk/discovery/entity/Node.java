/*******************************************************************************
 * Copyright 2017-2018 ZTE, Inc. and others.
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

    // 健康检查参数
    // health check type, allowableValues = "HTTP,TCP, TTL", example = "HTTP")
    private String checkType = "";
    // health check url, example for http "http://192.168.0.2:80/heallth", example for tcp
    // "192.168.1.100:80"
    private String checkUrl = "";

    // TCP or HTTP health check Interval,Unit: second", example = "10s"
    private String checkInterval;

    // TCP or HTTP health check TimeOut,Unit: second", example = "10s"
    private String checkTimeOut;


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

    /**
     * @return the checkType
     */
    public String getCheckType() {
        return checkType;
    }

    /**
     * @param checkType the checkType to set
     */
    public void setCheckType(String checkType) {
        this.checkType = checkType;
    }

    /**
     * @return the checkUrl
     */
    public String getCheckUrl() {
        return checkUrl;
    }

    /**
     * @param checkUrl the checkUrl to set
     */
    public void setCheckUrl(String checkUrl) {
        this.checkUrl = checkUrl;
    }

    /**
     * @return the checkInterval
     */
    public String getCheckInterval() {
        return checkInterval;
    }

    /**
     * @param checkInterval the checkInterval to set
     */
    public void setCheckInterval(String checkInterval) {
        this.checkInterval = checkInterval;
    }

    /**
     * @return the checkTimeOut
     */
    public String getCheckTimeOut() {
        return checkTimeOut;
    }

    /**
     * @param checkTimeOut the checkTimeOut to set
     */
    public void setCheckTimeOut(String checkTimeOut) {
        this.checkTimeOut = checkTimeOut;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return (new StringBuilder().append(ip).append(":").append(port).append("  ttl:").append(ttl)).toString();
    }

}
