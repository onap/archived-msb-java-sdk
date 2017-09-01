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
package org.onap.msb.sdk.example.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import org.onap.msb.sdk.example.springboot.model.Employee;;
 
@RestController
public class EmployeeController {
    @RequestMapping("/employee")
    public Employee getEmployees() {
        return (new Employee(1,"John","Kelly","john.kelly@gmail.com"));
    }
}