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
package org.onap.msb.sdk.httpclient.msb;

import java.util.concurrent.Callable;

import org.onap.msb.sdk.discovery.MSBService;
import org.onap.msb.sdk.discovery.common.RouteException;
import org.onap.msb.sdk.discovery.entity.MicroServiceFullInfo;
import org.onap.msb.sdk.discovery.entity.MicroServiceInfo;
import org.onap.msb.sdk.discovery.entity.NodeAddress;
import org.onap.msb.sdk.discovery.entity.RouteResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MSBServiceWrapperClient {

  private static final Logger logger = LoggerFactory.getLogger(MSBServiceWrapperClient.class);

  private String msbSvrAddress;

  private MSBService msbService = new MSBService();

  private String nameSpace;

  public MSBServiceWrapperClient(String msbSvrIp, int msbSvrPort) {
    super();
    this.msbSvrAddress = msbSvrIp + ":" + msbSvrPort;

    logger.info("msb service info:msbSvrAddress:{},nameSpace:{}", this.msbSvrAddress,
        this.nameSpace);

  }

  public MicroServiceFullInfo queryMicroServiceInfo(String serviceName, String version)
      throws RouteException {

    return invokeMsb(() -> msbService.queryMicroServiceInfo(msbSvrAddress, serviceName, version));


  }

  private <V> V invokeMsb(Callable<V> callable) throws RouteException {
    try {
      return callable.call();
    } catch (Exception e) {
      logger.error("msb service info:msbSvrAddress:" + this.msbSvrAddress, e);
      if (e instanceof RouteException) {
        throw (RouteException) e;
      } else {
        throw new RuntimeException(e);
      }
    }

  }


  /**
   * 注册微服务-默认追加方式,在msb上注册服务信息，服务的信息都可以填充在MicroServiceInfo上，包括：租户的信息等
   * 
   * @param microServiceInfo 微服务注册实体类
   * @throws RouteException
   * @return MicroServiceFullInfo
   */
  public MicroServiceFullInfo registerMicroServiceInfo(MicroServiceInfo msinfo)
      throws RouteException {

    return invokeMsb(() -> msbService.registerMicroServiceInfo(msbSvrAddress, msinfo));
  }



  /**
   * 注册微服务,在msb上注册服务信息，服务的信息都可以填充在MicroServiceInfo上，包括：租户的信息等
   * 
   * @param microServiceInfo 微服务注册实体类
   * @param createOrUpdate true：新增或追加更新 ,false：重新添加
   * @throws RouteException
   * @return MicroServiceFullInfo
   */
  public MicroServiceFullInfo registerMicroServiceInfo(MicroServiceInfo msinfo,
      boolean createOrUpdate) throws RouteException {

    return invokeMsb(
        () -> msbService.registerMicroServiceInfo(msbSvrAddress, msinfo, createOrUpdate));
  }



  /**
   * @Title cancelMicroServiceInfo
   * @Description TODO(注销全部微服务)
   * @param serviceName 服务名[必填,若自定义服务名包含/，用*代替]
   * @param version 版本号[若无版本号，传空字符串]
   * @throws RouteException
   * @return RouteResult
   */
  public RouteResult cancelMicroServiceInfo(String serviceName, String version)
      throws RouteException {
    return invokeMsb(() -> msbService.cancelMicroServiceInfo(msbSvrAddress, serviceName, version));
  }



  /**
   * 注销单个微服务
   * 
   * @param serviceName 服务名[必填,若自定义服务名包含/，用*代替]
   * @param version 版本号[若无版本号，传空字符串]
   * @param ip
   * @param port
   * @throws RouteException
   * @return RouteResult
   */

  public RouteResult cancelMicroServiceInfo(String serviceName, String version, String ip,
      String port) throws RouteException {

    return invokeMsb(
        () -> msbService.cancelMicroServiceInfo(msbSvrAddress, serviceName, version, ip, port));
  }


  public String getMsbSvrAddress() {
    return msbSvrAddress;
  }



  /**
   * 请求服务实例TTL健康检查，默认使用本租户的信息
   * 
   * @param serviceName 服务名
   * @param version 版本号[若无版本号，传空字符串]
   * @param ip 实例IP
   * @param port 实例端口
   * @throws RouteException
   * @return CheckNode
   */
  public NodeAddress healthCheckbyTTL(String serviceName, String version, String ip, String port)
      throws RouteException {

    return invokeMsb(
        () -> msbService.healthCheckbyTTL(msbSvrAddress, serviceName, version, ip, port));

  }



}
