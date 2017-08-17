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
/*
 * Copyright (C) 2015 Square, Inc.
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
 */
package org.onap.msb.sdk.httpclient.convert.jackson;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectReader;

import okhttp3.ResponseBody;
import retrofit2.Converter;

final class JacksonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

  private static final Logger logger = LoggerFactory.getLogger(JacksonResponseBodyConverter.class);

  private final ObjectReader adapter;



  JacksonResponseBodyConverter(ObjectReader adapter) {
    this.adapter = adapter;
  }

  @Override
  public T convert(ResponseBody value) throws IOException {
    String src = null;
    try {
      src = value.string();
      return adapter.readValue(src);
    } catch (IOException e) {
      logger.error("parse responseBody error,body:" + src);
      throw e;
    } finally {
      value.close();
    }
  }
}
