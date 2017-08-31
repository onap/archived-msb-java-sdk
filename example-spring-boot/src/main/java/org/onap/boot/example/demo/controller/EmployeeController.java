package org.onap.boot.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
 
import org.onap.boot.example.demo.model.Employee;;
 
@RestController
public class EmployeeController 
{
    @RequestMapping("/employee")
    public Employee getEmployees() 
    {
        //List<Employee> employeesList = new ArrayList<Employee>();
        //employeesList.add(new Employee(1,"John","Kelly","john.kelly@gmail.com"));
        return (new Employee(1,"John","Kelly","john.kelly@gmail.com"));
    }
}