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
package org.onap.msb.sdk.httpclient.lb;

import java.util.concurrent.atomic.AtomicLong;

import org.onap.msb.sdk.httpclient.ServiceHttpEndPointObject;
import org.onap.msb.sdk.httpclient.exception.RetrofitServiceRuntimeException;

/**
 * @author hu.rui
 *
 */
public class RoundRobinLBStrategy implements ILoadBalanceStrategy {

  private AtomicLong invokeCount = new AtomicLong(0);

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.zte.ums.zenap.httpclient.retrofit.lb.ILoadBalanceStrategy#chooseEndPointObject(com.zte.ums.
   * zenap.httpclient.retrofit.lb.LoadBalanceContext)
   */
  @Override
  public ServiceHttpEndPointObject chooseEndPointObject(LoadBalanceContext lbCtx) {

    long invokecount = invokeCount.getAndIncrement();

    if (lbCtx.getEndPoints().size() == 0) {
      throw new RetrofitServiceRuntimeException("target endPoints is empty");
    }

    int mod = (int) (invokecount % lbCtx.getEndPoints().size());

    return lbCtx.getEndPoints().get(mod);
  }

}
