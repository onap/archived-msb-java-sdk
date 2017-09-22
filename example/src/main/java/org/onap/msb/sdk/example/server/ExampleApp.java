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
package org.onap.msb.sdk.example.server;

import org.onap.msb.sdk.example.server.resources.AnimalResource;
import org.onap.msb.sdk.httpclient.msb.MSBServiceClient;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;


public class ExampleApp extends Application<Config> {

  private static final String CONFIGURATION_FILE = "example.yml";

  public static void main(String[] args) throws Exception {

    String configFile = null;
    if (args.length > 1) {
      configFile = args[0];
    } else {
      configFile = ExampleApp.class.getClassLoader().getResource(CONFIGURATION_FILE).getFile();
    }
    args = new String[] {"server", configFile};
    new ExampleApp().run(args);
  }

  @Override
  public void run(Config configuration, Environment environment) throws Exception {
    
    String MSB_IP="10.96.33.44";
    int MSB_Port=30080;
    
    environment.jersey().register(new AnimalResource());

    MSBServiceClient msbClient = new MSBServiceClient(MSB_IP, MSB_Port);

    MsbHelper helper = new MsbHelper(msbClient);
    helper.registerMsb();

  }

}
