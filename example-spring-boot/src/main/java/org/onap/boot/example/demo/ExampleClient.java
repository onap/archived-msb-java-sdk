package org.onap.boot.example.demo;

import java.io.IOException;

import org.onap.boot.example.demo.model.Employee;
import org.onap.msb.sdk.httpclient.RestServiceCreater;
import org.onap.msb.sdk.httpclient.msb.MSBServiceClient;

import retrofit2.Response;


public class ExampleClient {

  /**
   * @param args
   * @throws IOException
   */
  public static void main(String[] args) throws IOException {
    //For real use case, MSB IP and Port should come from configuration file instead of hard code here
    String MSB_IP="192.168.0.110";
    int MSB_Port=10081;

    MSBServiceClient msbClient = new MSBServiceClient(MSB_IP, MSB_Port);

    RestServiceCreater restServiceCreater =
        new RestServiceCreater(msbClient);

    EmployeeServiceClient implProxy =
        restServiceCreater.createService(EmployeeServiceClient.class);

    Employee employee = implProxy.queryEmployee().execute().body();
    System.out.println("Employee:" + employee);
  }

}
