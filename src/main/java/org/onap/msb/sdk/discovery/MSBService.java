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
package org.onap.msb.sdk.discovery;

import org.apache.commons.lang3.StringUtils;
import org.onap.msb.sdk.discovery.common.RouteConst;
import org.onap.msb.sdk.discovery.common.RouteException;
import org.onap.msb.sdk.discovery.entity.MicroServiceFullInfo;
import org.onap.msb.sdk.discovery.entity.MicroServiceInfo;
import org.onap.msb.sdk.discovery.entity.NodeAddress;
import org.onap.msb.sdk.discovery.entity.RouteResult;
import org.onap.msb.sdk.discovery.util.HttpClientUtil;
import org.onap.msb.sdk.discovery.util.JacksonJsonUtil;
import org.onap.msb.sdk.discovery.util.MsbUtil;
import org.onap.msb.sdk.discovery.util.RegExpTestUtil;



public class MSBService {

  /**
   * @Title queryMicroServiceInfo
   * @Description Get information of a service
   * @param msbAddress MSB Address
   * @param serviceName
   * @param version null if no version
   * @throws RouteException
   * @return MicroServiceFullInfo
   */
  public MicroServiceFullInfo queryMicroServiceInfo(String msbAddress, String serviceName,
      String version) throws RouteException {

	  MsbUtil.checkServiceName(serviceName);
	  version=MsbUtil.checkVersion(version);

    MicroServiceFullInfo microServiceInfo = null;


    String apiRouteUrl = (new StringBuilder().append("http://").append(msbAddress)
        .append(RouteConst.MSB_ROUTE_URL).append("/").append(serviceName).append("/version/")
        .append(version).append("?ifPassStatus=true")).toString();

    String resultJson = HttpClientUtil.httpGet(apiRouteUrl);
    microServiceInfo =
        (MicroServiceFullInfo) JacksonJsonUtil.jsonToBean(resultJson, MicroServiceFullInfo.class);

    return microServiceInfo;
  }


  /**
   * @Title registerMicroServiceInfo
   * @Description
   * @param msbAddress
   * @param microServiceInfo
   * @throws RouteException
   * @return MicroServiceFullInfo
   */
  public MicroServiceFullInfo registerMicroServiceInfo(String msbAddress,
      MicroServiceInfo microServiceInfo) throws RouteException {
    return this.registerMicroServiceInfo(msbAddress, microServiceInfo, true);

  }

  /**
   * @Title registerMicroServiceInfo
   * @Description
   * @param msbAddress
   * @param microServiceInfo
   * @param createOrUpdate true
   * @throws RouteException
   * @return MicroServiceFullInfo
   */
  public MicroServiceFullInfo registerMicroServiceInfo(String msbAddress,
      MicroServiceInfo microServiceInfo, boolean createOrUpdate) throws RouteException {

    if (StringUtils.isBlank(microServiceInfo.getServiceName())
        || StringUtils.isBlank(microServiceInfo.getProtocol())
        || microServiceInfo.getNodes().size() == 0) {

      throw new RouteException("register MicroServiceInfo FAIL： Some MicroServiceInfo's required fields are empty","DATA_FORMAT_ERROR");

    }


    if (StringUtils.isNotBlank(microServiceInfo.getVersion())) {
      if (!RegExpTestUtil.versionRegExpTest(microServiceInfo.getVersion())) {
        throw new RouteException("register MicroServiceInfo FAIL：version is not a valid  format","DATA_FORMAT_ERROR");
      }
    }



    if (!RouteConst.checkExistProtocol(microServiceInfo.getProtocol().trim())) {
      throw new RouteException("register MicroServiceInfo FAIL：Protocol is wrong,value range:("+ RouteConst.listProtocol() + ")", "DATA_FORMAT_ERROR");

    }



    String apiRouteJson = JacksonJsonUtil.beanToJson(microServiceInfo);

    String apiRouteUrl =
        (new StringBuilder().append("http://").append(msbAddress).append(RouteConst.MSB_ROUTE_URL)
            .append("?createOrUpdate=").append(createOrUpdate)).toString();

    String resultJson = HttpClientUtil.httpPostWithJSON(apiRouteUrl, apiRouteJson);

    MicroServiceFullInfo microServiceFullInfo =
        (MicroServiceFullInfo) JacksonJsonUtil.jsonToBean(resultJson, MicroServiceFullInfo.class);

    return microServiceFullInfo;
  }

  /**
   * @Title cancelMicroServiceInfo
   * @Description deregister a service
   * @param msbAddress
   * @param serviceName
   * @param version
   * @throws RouteException
   * @return RouteResult
   */
  public RouteResult cancelMicroServiceInfo(String msbAddress, String serviceName, String version)
      throws RouteException {
    RouteResult result = new RouteResult();

    MsbUtil.checkServiceName(serviceName);
    version=MsbUtil.checkVersion(version);



    String url =
        (new StringBuilder().append("http://").append(msbAddress).append(RouteConst.MSB_ROUTE_URL))
            .append("/").append(serviceName).append("/version/").append(version).toString();

    HttpClientUtil.delete(url, null);


    result.setResult(RouteConst.REQUEST_SUCCESS);
    result.setInfo("cancel MicroServiceInfo success");


    return result;
  }

  /**
   * @Title cancelMicroServiceInfo
   * @Description deregister a service
   * @param msbAddress
   * @param serviceName
   * @param version
   * @param ip
   * @param port
   * @throws RouteException
   * @return RouteResult
   */
  public RouteResult cancelMicroServiceInfo(String msbAddress, String serviceName, String version,
      String ip, String port) throws RouteException {

    RouteResult result = new RouteResult();

    MsbUtil.checkServiceName(serviceName);
    version=MsbUtil.checkVersion(version);

    String url =
        (new StringBuilder().append("http://").append(msbAddress).append(RouteConst.MSB_ROUTE_URL))
            .append("/").append(serviceName).append("/version/").append(version).append("/nodes/")
            .append(ip).append("/").append(port).toString();

    HttpClientUtil.delete(url, null);


    result.setResult(RouteConst.REQUEST_SUCCESS);
    result.setInfo("cancel MicroServiceInfo success");

    return result;
  }


  /**
   * @Title healthCheckbyTTL
   * @Description
   * @param msbAddress
   * @param serviceName
   * @param version
   * @param ip
   * @param port
   * @throws RouteException
   * @return CheckNode
   */
  public NodeAddress healthCheckbyTTL(String msbAddress, String serviceName, String version,
      String ip, String port) throws RouteException {

	  MsbUtil.checkServiceName(serviceName);
	  version=MsbUtil.checkVersion(version);
	  MsbUtil.checkHost(ip,port);


    NodeAddress checkNode = new NodeAddress(ip, port);


    String healthCheckJson = JacksonJsonUtil.beanToJson(checkNode);

    String healthCheckUrl =
        (new StringBuilder().append("http://").append(msbAddress).append(RouteConst.MSB_ROUTE_URL)
            .append("/").append(serviceName).append("/version/").append(version).append("/ttl"))
                .toString();

    HttpClientUtil.httpPutWithJSON(healthCheckUrl, healthCheckJson);

    return checkNode;
  }



}
