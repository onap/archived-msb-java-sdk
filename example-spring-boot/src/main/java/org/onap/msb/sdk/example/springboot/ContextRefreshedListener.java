/*******************************************************************************
 * Copyright 2017 Infosys Limited and others.
 *------------------------------------------------------------------------------
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package org.onap.msb.sdk.example.springboot;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.onap.msb.sdk.httpclient.msb.MSBServiceClient;
import org.onap.msb.sdk.example.springboot.common.MsbHelper;

@Component
public class ContextRefreshedListener implements ApplicationListener<ContextRefreshedEvent>{

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("Registring Service...");
        String MSB_IP="127.0.0.1";
        int MSB_Port=10081;

        MSBServiceClient msbClient = new MSBServiceClient(MSB_IP, MSB_Port);
        MsbHelper helper = new MsbHelper(msbClient);
        
        try {
			helper.registerMsb();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}