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
package org.onap.msb.sdk.httpclient.annotaion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 服务名和版本号不同，接口名就需要不同
 * 
 * @author hu.rui
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ServiceHttpEndPoint {

  // 在MSB上注册的服务名
  String serviceName();

  // 在MSB注册的版本号
  String serviceVersion();

  // 在通过msb转发时，所用的协议
  String msbProtocl() default "https";

  // 服务间点对点访问时，所用的协议
  String clientProtocl() default "http";

  // 服务所在的租户名
  String nameSpace() default "";

  // 服务的可见范围，系统间:“0”，系统内:“1”（默认）,可配置多个，以 | 分隔
  String visualRange() default "1";

  // 在MSB上注册的服务类型
  String serverType() default "api";

}
