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
/**
 * 
 */
package org.onap.msb.sdk.httpclient.builder;

import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import org.onap.msb.sdk.httpclient.ServiceHttpEndPointObject;
import org.onap.msb.sdk.httpclient.exception.RetrofitServiceRuntimeException;

/**
 * @author 10071214
 *
 */
public interface IRetrofitObjectBuilder {

  Map<ServiceHttpEndPointObject, Object> buildRetrofitObject(
      AtomicReference<Map<ServiceHttpEndPointObject, Object>> endPointToRetrofitRef,
      ServiceHttpEndPointObject lastEndPoint) throws RetrofitServiceRuntimeException;


}
