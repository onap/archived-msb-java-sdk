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
package org.onap.msb.sdk.discovery;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.onap.msb.sdk.discovery.common.RouteException;
import org.onap.msb.sdk.discovery.entity.MicroServiceFullInfo;
import org.onap.msb.sdk.discovery.entity.MicroServiceInfo;
import org.onap.msb.sdk.discovery.entity.Node;
import org.onap.msb.sdk.discovery.util.HttpClientUtil;
import org.onap.msb.sdk.discovery.util.JacksonJsonUtil;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({HttpClientUtil.class})
public class MSBServiceTest {
    private static final String mockMSBUrl1 =
                    "http://127.0.0.1:10081/api/microservices/v1/services?createOrUpdate=true";
    private static final String mockMSBUrl2 =
                    "http://127.0.0.1:10081/api/microservices/v1/services?createOrUpdate=false";
    private static final String mockRegistrationJson =
                    "{\"serviceName\":\"aai\",\"version\":\"v8\",\"url\":\"/aai/v8\",\"protocol\":\"REST\",\"visualRange\":\"1\",\"lb_policy\":\"\",\"path\":\"/aai/v8\",\"nodes\":[{\"ip\":\"10.74.44.1\",\"port\":\"8443\",\"ttl\":\"\"}],\"metadata\":null}";

    @Test
    public void test_registration() throws RouteException {
        String msbAddress = "127.0.0.1:10081";
        MicroServiceInfo microServiceInfo =
                        (MicroServiceInfo) JacksonJsonUtil.jsonToBean(mockRegistrationJson, MicroServiceInfo.class);
        MicroServiceFullInfo microServiceFullInfo = mockMicroServiceFullInfo(microServiceInfo);
        mockGetRest(mockMSBUrl1, JacksonJsonUtil.beanToJson(microServiceFullInfo));
        MSBService msbService = new MSBService();
        microServiceFullInfo = msbService.registerMicroServiceInfo(msbAddress, microServiceInfo);
        Assert.assertTrue(microServiceFullInfo.getPath().equals("/aai/v8"));
        Assert.assertTrue(microServiceFullInfo.getProtocol().equals("REST"));
        Assert.assertTrue(microServiceFullInfo.getServiceName().equals("aai"));
        Assert.assertTrue(microServiceFullInfo.getUrl().equals("/aai/v8"));
        Assert.assertTrue(microServiceFullInfo.getVersion().equals("v8"));
        Assert.assertTrue(microServiceFullInfo.getVisualRange().equals("1"));
    }

    @Test
    public void test_registration_update_false() throws RouteException {
        String msbAddress = "127.0.0.1:10081";
        MicroServiceInfo microServiceInfo =
                        (MicroServiceInfo) JacksonJsonUtil.jsonToBean(mockRegistrationJson, MicroServiceInfo.class);
        MicroServiceFullInfo microServiceFullInfo = mockMicroServiceFullInfo(microServiceInfo);
        mockGetRest(mockMSBUrl2, JacksonJsonUtil.beanToJson(microServiceFullInfo));
        MSBService msbService = new MSBService();
        microServiceFullInfo = msbService.registerMicroServiceInfo(msbAddress, microServiceInfo, false);
        Assert.assertTrue(microServiceFullInfo.getPath().equals("/aai/v8"));
        Assert.assertTrue(microServiceFullInfo.getProtocol().equals("REST"));
        Assert.assertTrue(microServiceFullInfo.getServiceName().equals("aai"));
        Assert.assertTrue(microServiceFullInfo.getUrl().equals("/aai/v8"));
        Assert.assertTrue(microServiceFullInfo.getVersion().equals("v8"));
        Assert.assertTrue(microServiceFullInfo.getVisualRange().equals("1"));
    }


    private MicroServiceFullInfo mockMicroServiceFullInfo(MicroServiceInfo info) {
        MicroServiceFullInfo serviceInfo = new MicroServiceFullInfo();
        serviceInfo.setServiceName(info.getServiceName());
        serviceInfo.setVersion(info.getVersion());
        serviceInfo.setUrl(info.getUrl());
        serviceInfo.setProtocol(info.getProtocol());
        serviceInfo.setVisualRange(info.getVisualRange());
        serviceInfo.setLb_policy(info.getLb_policy());
        serviceInfo.setPath(info.getPath());
        return serviceInfo;
    }


    private void mockGetRest(String mockMSBUrl, String mockServiceInfoJson) throws RouteException {
        PowerMockito.mockStatic(HttpClientUtil.class);
        PowerMockito.when(HttpClientUtil.httpPostWithJSON(mockMSBUrl, mockRegistrationJson))
                        .thenReturn(mockServiceInfoJson);
    }
}
