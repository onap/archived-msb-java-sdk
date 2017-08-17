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

import org.onap.msb.sdk.httpclient.ServiceHttpEndPointObject;

/**
 * 每个实际的策略对象在运行中会保持单例
 * 
 * @author hu.rui
 *
 */
public interface ILoadBalanceStrategy {

  ServiceHttpEndPointObject chooseEndPointObject(LoadBalanceContext lbCtx);

}
