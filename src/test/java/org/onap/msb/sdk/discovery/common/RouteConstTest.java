/**
 * Copyright 2017 ZTE Corporation.
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

package org.onap.msb.sdk.discovery.common;

import org.junit.Assert;
import org.junit.Test;

public class RouteConstTest {

	@Test
	public void test_checkExistProtocol(){
		
        Assert.assertTrue(RouteConst.checkExistProtocol("FTP"));
        Assert.assertFalse(RouteConst.checkExistProtocol("FTTP"));
	}
	
	@Test
	public void test_checkExist(){
		
        Assert.assertTrue(RouteConst.checkExist("test,tt,ss","tt"));
        Assert.assertFalse(RouteConst.checkExist("test,tt,ss","ee"));
	}
	
	@Test
	public void test_listProtocol(){
		String list="REST | HTTP | MQ | FTP | SNMP | TCP | UDP | UI | ";
		Assert.assertEquals(list, RouteConst.listProtocol());
	}
}
