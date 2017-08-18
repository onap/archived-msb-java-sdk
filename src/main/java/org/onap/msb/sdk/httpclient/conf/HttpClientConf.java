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
package org.onap.msb.sdk.httpclient.conf;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HttpClientConf {

  @JsonProperty
  private long readTimeout = 180 * 1000;

  @JsonProperty
  private long connectTimeout = 20 * 1000;

  @JsonProperty
  private long writeTimeout = 10 * 1000;


  public long getReadTimeout() {
    return readTimeout;
  }

  public long getConnectTimeout() {
    return connectTimeout;
  }

  public long getWriteTimeout() {
    return writeTimeout;
  }

  public void setReadTimeout(long readTimeout) {
    this.readTimeout = readTimeout;
  }

  public void setConnectTimeout(long connectTimeout) {
    this.connectTimeout = connectTimeout;
  }

  public void setWriteTimeout(long writeTimeout) {
    this.writeTimeout = writeTimeout;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (int) (connectTimeout ^ (connectTimeout >>> 32));
    result = prime * result + (int) (readTimeout ^ (readTimeout >>> 32));
    result = prime * result + (int) (writeTimeout ^ (writeTimeout >>> 32));
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    HttpClientConf other = (HttpClientConf) obj;
    if (connectTimeout != other.connectTimeout)
      return false;
    if (readTimeout != other.readTimeout)
      return false;
    if (writeTimeout != other.writeTimeout)
      return false;
    return true;
  }



}
