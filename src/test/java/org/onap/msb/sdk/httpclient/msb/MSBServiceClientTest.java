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
package org.onap.msb.sdk.httpclient.msb;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.onap.msb.sdk.discovery.common.RouteException;
import org.onap.msb.sdk.discovery.entity.MicroServiceFullInfo;
import org.onap.msb.sdk.discovery.entity.MicroServiceInfo;
import org.onap.msb.sdk.discovery.util.HttpClientUtil;
import org.onap.msb.sdk.discovery.util.JacksonJsonUtil;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({HttpClientUtil.class})
public class MSBServiceClientTest {
    private static final String MOCK_MSB_URL_REG_UPDATE_TRUE =
                    "http://127.0.0.1:10081/api/microservices/v1/services?createOrUpdate=true";
    private static final String MOCK_MSB_URL_REG_UPDATE_FALSE =
                    "http://127.0.0.1:10081/api/microservices/v1/services?createOrUpdate=false";
    private static final String MOCK_MSB_URL_DIS =
                    "http://127.0.0.1:10081/api/microservices/v1/services/aai/version/v8?ifPassStatus=true";
    private static final String MOCK_MSB_URL_UNREG =
                    "http://127.0.0.1:10081/api/microservices/v1/services/aai/version/v8";

    private static final String MOCK_REG_SERVICE_JSON =
                    "{\"serviceName\":\"aai\",\"version\":\"v8\",\"url\":\"/aai/v8\",\"protocol\":\"REST\",\"visualRange\":\"1\",\"lb_policy\":\"\",\"path\":\"/aai/v8\",\"nodes\":[{\"ip\":\"10.74.44.1\",\"port\":\"8443\",\"ttl\":\"\"}],\"metadata\":null}";

    @Test
    public void test_registration_update_true() throws RouteException {
        MicroServiceInfo microServiceInfo =
                        (MicroServiceInfo) JacksonJsonUtil.jsonToBean(MOCK_REG_SERVICE_JSON, MicroServiceInfo.class);
        MicroServiceFullInfo microServiceFullInfo = mockMicroServiceFullInfo(microServiceInfo);
        mockHttpPost(MOCK_MSB_URL_REG_UPDATE_TRUE, JacksonJsonUtil.beanToJson(microServiceFullInfo));
        MSBServiceClient msbClient = new MSBServiceClient("127.0.0.1", 10081);
        microServiceFullInfo = msbClient.registerMicroServiceInfo(microServiceInfo);
        Assert.assertTrue(microServiceFullInfo.getPath().equals("/aai/v8"));
        Assert.assertTrue(microServiceFullInfo.getProtocol().equals("REST"));
        Assert.assertTrue(microServiceFullInfo.getServiceName().equals("aai"));
        Assert.assertTrue(microServiceFullInfo.getUrl().equals("/aai/v8"));
        Assert.assertTrue(microServiceFullInfo.getVersion().equals("v8"));
        Assert.assertTrue(microServiceFullInfo.getVisualRange().equals("1"));
    }

    @Test
    public void test_registration_update_false() throws RouteException {
        MicroServiceInfo microServiceInfo =
                        (MicroServiceInfo) JacksonJsonUtil.jsonToBean(MOCK_REG_SERVICE_JSON, MicroServiceInfo.class);
        MicroServiceFullInfo microServiceFullInfo = mockMicroServiceFullInfo(microServiceInfo);
        mockHttpPost(MOCK_MSB_URL_REG_UPDATE_FALSE, JacksonJsonUtil.beanToJson(microServiceFullInfo));
        MSBServiceClient msbClient = new MSBServiceClient("127.0.0.1", 10081);
        microServiceFullInfo = msbClient.registerMicroServiceInfo(microServiceInfo, false);
        Assert.assertTrue(microServiceFullInfo.getPath().equals("/aai/v8"));
        Assert.assertTrue(microServiceFullInfo.getProtocol().equals("REST"));
        Assert.assertTrue(microServiceFullInfo.getServiceName().equals("aai"));
        Assert.assertTrue(microServiceFullInfo.getUrl().equals("/aai/v8"));
        Assert.assertTrue(microServiceFullInfo.getVersion().equals("v8"));
        Assert.assertTrue(microServiceFullInfo.getVisualRange().equals("1"));
    }

    @Test
    public void test_discovery() throws RouteException {
        MicroServiceInfo microServiceInfo =
                        (MicroServiceInfo) JacksonJsonUtil.jsonToBean(MOCK_REG_SERVICE_JSON, MicroServiceInfo.class);
        MicroServiceFullInfo microServiceFullInfo = mockMicroServiceFullInfo(microServiceInfo);
        mockHttpGet(MOCK_MSB_URL_DIS, JacksonJsonUtil.beanToJson(microServiceFullInfo));

        MSBServiceClient msbClient = new MSBServiceClient("127.0.0.1", 10081);
        microServiceFullInfo = msbClient.queryMicroServiceInfo("aai", "v8");
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


    private void mockHttpPost(String mockMSBUrl, String mockServiceInfoJson) throws RouteException {
        PowerMockito.mockStatic(HttpClientUtil.class);
        PowerMockito.when(HttpClientUtil.httpPostWithJSON(mockMSBUrl, MOCK_REG_SERVICE_JSON))
                        .thenReturn(mockServiceInfoJson);
    }

    private void mockHttpGet(String mockMSBUrl, String mockServiceInfoJson) throws RouteException {
        PowerMockito.mockStatic(HttpClientUtil.class);
        PowerMockito.when(HttpClientUtil.httpGet(mockMSBUrl)).thenReturn(mockServiceInfoJson);
    }

}
