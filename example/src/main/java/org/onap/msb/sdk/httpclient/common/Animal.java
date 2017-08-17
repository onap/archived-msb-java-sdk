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
package org.onap.msb.sdk.httpclient.common;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Animal implements Serializable {

  private static final long serialVersionUID = -717235590728668809L;

  @JsonProperty
  private String type;

  @JsonProperty
  private String name;

  @JsonProperty
  private int age;

  public Animal() {}

  public Animal(String type, String name, int age) {
    this.type = type;
    this.name = name;
    this.age = age;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  @Override
  public String toString() {
    return "Animal{" + "type='" + type + '\'' + ", name='" + name + '\'' + ", age=" + age + '}';
  }
}
