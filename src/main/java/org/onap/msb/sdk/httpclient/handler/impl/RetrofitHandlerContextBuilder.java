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
package org.onap.msb.sdk.httpclient.handler.impl;

import org.onap.msb.sdk.httpclient.builder.impl.ClientRetrofitObjectBuilder;
import org.onap.msb.sdk.httpclient.exception.RetrofitServiceRuntimeException;
import org.onap.msb.sdk.httpclient.handler.HandlerContextBuilder;
import org.onap.msb.sdk.httpclient.handler.RetrofitServiceHandlerContext;

/**
 * @author 10071214
 *
 */
public class RetrofitHandlerContextBuilder implements HandlerContextBuilder {

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.zte.ums.zenap.httpclient.retrofit.handler.HandlerContextBuilder#build(com.zte.ums.zenap.
   * httpclient.retrofit.handler.RetrofitServiceHandlerContext)
   */
  @Override
  public void build(RetrofitServiceHandlerContext ctx) throws RetrofitServiceRuntimeException {

    // if (ctx.getLbStyle().equals(LBSTYLE.MSB)) {
    //
    // ctx.setRetrofitObjectBuilder(new MsbRetrofitObjectBuilder(ctx));
    // } else {
    ctx.setRetrofitObjectBuilder(new ClientRetrofitObjectBuilder(ctx));
    // }
  }

}
