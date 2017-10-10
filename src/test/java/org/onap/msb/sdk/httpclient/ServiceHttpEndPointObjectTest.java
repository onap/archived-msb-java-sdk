/**
 * Copyright 2017 ZTE Corporation.
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
package org.onap.msb.sdk.httpclient;

import org.junit.Assert;
import org.junit.Test;
import org.onap.msb.sdk.discovery.entity.MicroServiceFullInfo;
import org.onap.msb.sdk.discovery.entity.NodeInfo;

public class ServiceHttpEndPointObjectTest {

    @Test
    public void testBean() {
        NodeInfo nodeInfo = new NodeInfo();
        nodeInfo.setIp("127.0.0.1");
        nodeInfo.setPort("80");
        MicroServiceFullInfo cloneFullInfo = new MicroServiceFullInfo();
        cloneFullInfo.setUrl("/aai/v8");
        cloneFullInfo.setServiceName("aai");
        cloneFullInfo.setVersion("v8");
        cloneFullInfo.setVisualRange("1");

        ServiceHttpEndPointObject endPointOjb = new ServiceHttpEndPointObject("aai", "v8", nodeInfo, cloneFullInfo);
        Assert.assertEquals(endPointOjb.getIp(), "127.0.0.1");
        Assert.assertEquals(endPointOjb.getPort(), "80");
        Assert.assertEquals(endPointOjb.getServiceName(), "aai");
        Assert.assertEquals(endPointOjb.getServiceVersion(), "v8");
    }

}
