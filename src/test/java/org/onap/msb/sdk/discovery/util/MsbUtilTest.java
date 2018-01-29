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

package org.onap.msb.sdk.discovery.util;

import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.onap.msb.sdk.discovery.common.RouteException;
import org.onap.msb.sdk.discovery.entity.Node;
import org.onap.msb.sdk.discovery.entity.NodeInfo;

public class MsbUtilTest {

	@Test
	public void test_getConsulServiceName(){
		Assert.assertEquals("test", MsbUtil.getConsulServiceName("test",""));
		Assert.assertEquals("test-ns", MsbUtil.getConsulServiceName("test","ns"));
	}
	
	@Test
	public void test_getLbNodes(){
		
		Node node=new Node();
		node.setIp("127.0.0.1");
		node.setPort("8089");
		
		Set<NodeInfo> nodes=MsbUtil.getLbNodes(node);
		Assert.assertEquals(1, nodes.size());
	}
	
	@Test
	public void test_checkServiceName(){
		try {
			MsbUtil.checkServiceName("testName");
			MsbUtil.checkServiceName("");

		} catch (RouteException e) {
    		Assert.assertTrue(e instanceof RouteException);

		}
		
	}
	
	@Test
	public void test_checkVersion(){
		try {
			Assert.assertEquals("v1", MsbUtil.checkVersion("v1"));
			Assert.assertEquals("null", MsbUtil.checkVersion(""));
			 MsbUtil.checkVersion("version");

		} catch (RouteException e) {
    		Assert.assertTrue(e instanceof RouteException);

		}
		
	}
	
	@Test
	public void test_checkHost(){
		try {
			MsbUtil.checkHost("127.0.0.1","2565");
			MsbUtil.checkHost("127.0.0.1","");
		} catch (RouteException e) {
    		Assert.assertTrue(e instanceof RouteException);
    		Assert.assertEquals("DATA_FORMAT_ERROR",e.getErrorCode());
    		Assert.assertNotEquals("ERROR",e.getErrorMsg());
    		
    		e.setErrorCode("DATA_FORMAT_ERROR_TEST");
    		e.setErrorMsg("ERROR");
    		Assert.assertNotEquals("DATA_FORMAT_ERROR",e.getErrorCode());
    		Assert.assertEquals("ERROR",e.getErrorMsg());

		}
	}
	
	@Test
	public void test_checkIp(){
		try {
			MsbUtil.checkHost("","2565");
		} catch (RouteException e) {
    		Assert.assertTrue(e instanceof RouteException);
    		

		}
	}
}
