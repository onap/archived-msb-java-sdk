/**
 * Copyright 2016-2017 ZTE, Inc. and others.
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
 */

package org.onap.msb.sdk.discovery.util;

import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.onap.msb.sdk.discovery.common.RouteException;
import org.onap.msb.sdk.discovery.entity.NodeAddress;


public class JacksonJsonUtilTest {
    @Test
    public void testBeanToJson() {
        try {
            NodeAddress address = new NodeAddress("127.0.0.1", "80");
            String json = JacksonJsonUtil.beanToJson(address);
            Assert.assertEquals("{\"ip\":\"127.0.0.1\",\"port\":\"80\"}", json);
        } catch (Exception e) {
            Assert.fail("Exception" + e.getMessage());
        }
    }

    @Test
    public void testJsonToBean() {
        try {
            String json = "{\"ip\":\"127.0.0.1\",\"port\":\"80\"}";
            NodeAddress address = (NodeAddress) JacksonJsonUtil.jsonToBean(json, NodeAddress.class);
            Assert.assertEquals("127.0.0.1", address.getIp());
            Assert.assertEquals("80", address.getPort());
        } catch (Exception e) {
            Assert.fail("Exception" + e.getMessage());
        }
    }
    
    @Test
    public void testJsonToBean_fail() {
        try {
            String json = "{\"ip\":\"127.0.0.1,\"port\":\"80\"}";
            NodeAddress address = (NodeAddress) JacksonJsonUtil.jsonToBean(json, NodeAddress.class);
            Assert.assertEquals("127.0.0.1", address.getIp());
        } catch (Exception e) {
        	Assert.assertTrue(e instanceof RouteException);
        }
    }
}
