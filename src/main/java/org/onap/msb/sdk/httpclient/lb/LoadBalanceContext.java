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

import java.lang.reflect.Method;
import java.util.List;

import org.onap.msb.sdk.httpclient.ServiceHttpEndPointObject;

/**
 * @author hu.rui
 *
 */
public class LoadBalanceContext {

  private List<ServiceHttpEndPointObject> endPoints;

  private Object[] args;

  private Method method;


  public Method getMethod() {
    return method;
  }

  public void setMethod(Method method) {
    this.method = method;
  }

  public List<ServiceHttpEndPointObject> getEndPoints() {
    return endPoints;
  }

  public void setEndPoints(List<ServiceHttpEndPointObject> endPoints) {
    this.endPoints = endPoints;
  }

  public Object[] getArgs() {
    return args;
  }

  public void setArgs(Object[] args) {
    this.args = args;
  }



}
