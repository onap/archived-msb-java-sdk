/*
 * ============LICENSE_START==========================================
 * ONAP Portal SDK
 * ===================================================================
 * Copyright © 2017 IBM Intellectual Property. All rights reserved.
 * ===================================================================
 *
 * Unless otherwise specified, all software contained herein is licensed
 * under the Apache License, Version 2.0 (the "License");
 * you may not use this software except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *             http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Unless otherwise specified, all documentation contained herein is licensed
 * under the Creative Commons License, Attribution 4.0 Intl. (the "License");
 * you may not use this documentation except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *             https://creativecommons.org/licenses/by/4.0/
 *
 * Unless required by applicable law or agreed to in writing, documentation
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ============LICENSE_END============================================
 *
 * 
 */
package org.onap.msb.sdk.httpclient.lb;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.onap.msb.sdk.discovery.entity.MicroServiceFullInfo;
import org.onap.msb.sdk.discovery.entity.NodeInfo;
import org.onap.msb.sdk.httpclient.ServiceHttpEndPointObject;

public class LoadBalanceContextTest {
	@Test
	public void dbTest() {
		LoadBalanceContext lbc = new LoadBalanceContext();
		List<ServiceHttpEndPointObject> endPoints = new ArrayList<>();
		ServiceHttpEndPointObject shep = new ServiceHttpEndPointObject("test", "1.0", new NodeInfo(),
				new MicroServiceFullInfo());
		endPoints.add(shep);
		lbc.setEndPoints(endPoints);
		Assert.assertNotNull(lbc.getEndPoints());
		Object[] args = { "arg11", "arg2" };
		lbc.setArgs(args);
		Assert.assertNotNull(lbc.getArgs());
	}
}
