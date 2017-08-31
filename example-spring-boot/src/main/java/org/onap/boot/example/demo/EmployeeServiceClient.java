package org.onap.boot.example.demo;

import org.onap.boot.example.demo.model.Employee;
import org.onap.msb.sdk.httpclient.annotaion.ServiceHttpEndPoint;

import retrofit2.Call;
import retrofit2.http.GET;

@ServiceHttpEndPoint(serviceName = "employee", serviceVersion = "v1")
public interface EmployeeServiceClient {
	  @GET("employee")
	  Call<Employee> queryEmployee();
}
