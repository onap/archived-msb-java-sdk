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
package org.onap.msb.sdk.httpclient.convert.jackson;

import org.onap.msb.sdk.httpclient.convert.IConverterFactoryBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import retrofit2.Converter.Factory;

/**
 * @author hu.rui
 *
 */
public class JacksonConverterFactoryBuilder implements IConverterFactoryBuilder {

  private static ObjectMapper objectMapper = new ObjectMapper();
  static {
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
  }


  /*
   * (non-Javadoc)
   * 
   * @see
   * com.zte.ums.zenap.httpclient.retrofit.convert.IConverterFactoryBuilder#buildConverterFactory()
   */
  @Override
  public Factory buildConverterFactory() {
    return JacksonConverterFactory.create(objectMapper);
  }

}
