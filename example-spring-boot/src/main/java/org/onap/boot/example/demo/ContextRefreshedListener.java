package org.onap.boot.example.demo;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.onap.msb.sdk.httpclient.msb.MSBServiceClient;
import org.onap.boot.example.demo.msb.MsbHelper;

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