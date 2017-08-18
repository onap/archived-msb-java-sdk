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


public class MSBServiceClient {

  private static final Logger logger = LoggerFactory.getLogger(MSBServiceClient.class);

  private String msbSvrAddress;

  private MSBService msbService = new MSBService();

  public MSBServiceClient(String msbSvrIp, int msbSvrPort) {
    super();
    this.msbSvrAddress = msbSvrIp + ":" + msbSvrPort;

    logger.info("msb service info:msbSvrAddress:{}", this.msbSvrAddress);
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


  public MicroServiceFullInfo registerMicroServiceInfo(MicroServiceInfo msinfo)
      throws RouteException {

    return invokeMsb(() -> msbService.registerMicroServiceInfo(msbSvrAddress, msinfo));
  }



  public MicroServiceFullInfo registerMicroServiceInfo(MicroServiceInfo msinfo,
      boolean createOrUpdate) throws RouteException {

    return invokeMsb(
        () -> msbService.registerMicroServiceInfo(msbSvrAddress, msinfo, createOrUpdate));
  }



  /**
   * unregister all the instances of a service
   */
  public RouteResult cancelMicroServiceInfo(String serviceName, String version)
      throws RouteException {
    return invokeMsb(() -> msbService.cancelMicroServiceInfo(msbSvrAddress, serviceName, version));
  }



  /**
   * unregister the specified instance of a service
   */

  public RouteResult cancelMicroServiceInfo(String serviceName, String version, String ip,
      String port) throws RouteException {

    return invokeMsb(
        () -> msbService.cancelMicroServiceInfo(msbSvrAddress, serviceName, version, ip, port));
  }


  public String getMsbSvrAddress() {
    return msbSvrAddress;
  }

}
