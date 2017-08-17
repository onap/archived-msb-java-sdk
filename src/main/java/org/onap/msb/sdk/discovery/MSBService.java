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
import org.onap.msb.sdk.discovery.util.RegExpTestUtil;



public class MSBService {

  /**
   * @Title queryMicroServiceInfo
   * @Description TODO(查询单个微服务)
   * @param msbAddress 微服务服务器地址( ip:port 或 域名地址)
   * @param serviceName 服务名[必填,若自定义服务名包含/，用*代替]
   * @param version 版本号[若无版本号，传空字符串]
   * @throws RouteException
   * @return MicroServiceFullInfo
   */
  public MicroServiceFullInfo queryMicroServiceInfo(String msbAddress, String serviceName,
      String version) throws RouteException {

    // 服务名空值检查
    if (StringUtils.isBlank(serviceName)) {
      throw new RouteException("ServiceName  can't be empty", "DATA_FORMAT_ERROR");
    }

    // 版本号格式检查
    if (StringUtils.isNotBlank(version)) {
      if (!RegExpTestUtil.versionRegExpTest(version)) {
        throw new RouteException("version is not a valid  format", "DATA_FORMAT_ERROR");
      }
    } else {
      version = "null";
    }

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
   * @Description TODO(注册微服务-默认追加方式)
   * @param msbAddress 微服务服务器地址( ip:port 或 域名地址)
   * @param microServiceInfo 微服务注册实体类
   * @throws RouteException
   * @return MicroServiceFullInfo
   */
  public MicroServiceFullInfo registerMicroServiceInfo(String msbAddress,
      MicroServiceInfo microServiceInfo) throws RouteException {
    return this.registerMicroServiceInfo(msbAddress, microServiceInfo, true);

  }

  /**
   * @Title registerMicroServiceInfo
   * @Description TODO(注册微服务)
   * @param msbAddress 微服务服务器地址( ip:port 或 域名地址)
   * @param microServiceInfo 微服务注册实体类
   * @param createOrUpdate true：新增或追加更新 ,false：重新添加
   * @throws RouteException
   * @return MicroServiceFullInfo
   */
  public MicroServiceFullInfo registerMicroServiceInfo(String msbAddress,
      MicroServiceInfo microServiceInfo, boolean createOrUpdate) throws RouteException {

    // 必填项空值检查
    if (StringUtils.isBlank(microServiceInfo.getServiceName())
        || StringUtils.isBlank(microServiceInfo.getProtocol())
        || microServiceInfo.getNodes().size() == 0) {

      throw new RouteException(
          "register MicroServiceInfo FAIL： Some MicroServiceInfo's required fields are empty",
          "DATA_FORMAT_ERROR");

    }


    // 版本号格式检查
    if (StringUtils.isNotBlank(microServiceInfo.getVersion())) {
      if (!RegExpTestUtil.versionRegExpTest(microServiceInfo.getVersion())) {
        throw new RouteException("register MicroServiceInfo FAIL：version is not a valid  format",
            "DATA_FORMAT_ERROR");

      }
    }



    // 服务协议取值范围检查
    if (!RouteConst.checkExistProtocol(microServiceInfo.getProtocol().trim())) {
      throw new RouteException("register MicroServiceInfo FAIL：Protocol is wrong,value range:("
          + RouteConst.listProtocol() + ")", "DATA_FORMAT_ERROR");

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
   * @Description TODO(注销全部微服务)
   * @param msbAddress 微服务服务器地址( ip:port 或 域名地址)
   * @param serviceName 服务名[必填,若自定义服务名包含/，用*代替]
   * @param version 版本号[若无版本号，传空字符串]
   * @throws RouteException
   * @return RouteResult
   */
  public RouteResult cancelMicroServiceInfo(String msbAddress, String serviceName, String version)
      throws RouteException {
    RouteResult result = new RouteResult();

    // 服务名空值检查
    if (StringUtils.isBlank(serviceName)) {
      throw new RouteException("cancel MicroServiceInfo FAIL：ServiceName  can't be empty",
          "DATA_FORMAT_ERROR");

    }

    // 版本号格式检查
    if (StringUtils.isNotBlank(version)) {
      if (!RegExpTestUtil.versionRegExpTest(version)) {
        throw new RouteException("cancel MicroServiceInfo FAIL：version is not a valid  format",
            "DATA_FORMAT_ERROR");

      }
    } else {
      version = "null";
    }


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
   * @Description TODO(注销单个微服务)
   * @param msbAddress 微服务服务器地址( ip:port 或 域名地址)
   * @param serviceName 服务名[必填,若自定义服务名包含/，用*代替]
   * @param version 版本号[若无版本号，传空字符串]
   * @param ip
   * @param port
   * @throws RouteException
   * @return RouteResult
   */
  public RouteResult cancelMicroServiceInfo(String msbAddress, String serviceName, String version,
      String ip, String port) throws RouteException {

    RouteResult result = new RouteResult();

    // 服务名空值检查
    if (StringUtils.isBlank(serviceName)) {
      throw new RouteException("cancel MicroServiceInfo FAIL：ServiceName  can't be empty",
          "DATA_FORMAT_ERROR");

    }


    // HOST空值和格式检查
    if (StringUtils.isBlank(ip)) {
      throw new RouteException("cancel MicroServiceInfo FAIL：ip can't be empty",
          "DATA_FORMAT_ERROR");
    }

    if (StringUtils.isBlank(port)) {
      throw new RouteException("cancel MicroServiceInfo FAIL：port can't be empty",
          "DATA_FORMAT_ERROR");

    }

    // 版本号格式检查
    if (StringUtils.isNotBlank(version)) {
      if (!RegExpTestUtil.versionRegExpTest(version)) {
        throw new RouteException("cancel MicroServiceInfo FAIL：version is not a valid  format",
            "DATA_FORMAT_ERROR");
      }
    } else {
      version = "null";
    }


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
   * @Description TODO(请求服务实例TTL健康检查)
   * @param msbAddress
   * @param serviceName 服务名
   * @param version 版本号[若无版本号，传空字符串]
   * @param ip 实例IP
   * @param port 实例端口
   * @throws RouteException
   * @return CheckNode
   */
  public NodeAddress healthCheckbyTTL(String msbAddress, String serviceName, String version,
      String ip, String port) throws RouteException {

    // 服务名空值检查
    if (StringUtils.isBlank(serviceName)) {
      throw new RouteException("ServiceName  can't be empty", "DATA_FORMAT_ERROR");
    }

    // 版本号格式检查
    if (StringUtils.isNotBlank(version)) {
      if (!RegExpTestUtil.versionRegExpTest(version)) {
        throw new RouteException("version is not a valid  format", "DATA_FORMAT_ERROR");
      }
    } else {
      version = "null";
    }


    // HOST空值和格式检查
    if (StringUtils.isBlank(ip)) {
      throw new RouteException("healthCheck by TTL FAIL：ip can't be empty", "DATA_FORMAT_ERROR");

    }

    if (StringUtils.isBlank(port)) {
      throw new RouteException("healthCheck by TTL FAIL：port can't be empty", "DATA_FORMAT_ERROR");

    }



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
