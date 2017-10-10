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
package org.onap.msb.sdk.httpclient.conf;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

public class HttpClientConfTest {

    @Test
    public void testEquals() {
        HttpClientConf conf1 = new HttpClientConf();
        conf1.setConnectTimeout(100000);
        conf1.setReadTimeout(200000);
        conf1.setWriteTimeout(30000);

        HttpClientConf conf2 = new HttpClientConf();
        conf2.setConnectTimeout(100000);
        conf2.setReadTimeout(200000);
        conf2.setWriteTimeout(30000);

        Assert.assertEquals(conf1, conf2);

        conf2.setWriteTimeout(10000);
        Assert.assertNotEquals(conf1, conf2);
    }

    @Test
    public void testHashCode() {
        HttpClientConf conf1 = new HttpClientConf();
        conf1.setConnectTimeout(100000);
        conf1.setReadTimeout(200000);
        conf1.setWriteTimeout(30000);

        HttpClientConf conf2 = new HttpClientConf();
        conf2.setConnectTimeout(100000);
        conf2.setReadTimeout(200000);
        conf2.setWriteTimeout(30000);

        Set set = new HashSet();
        set.add(conf1);
        set.add(conf2);

        Assert.assertEquals(set.size(), 1);

        HttpClientConf conf3 = new HttpClientConf();
        conf3.setConnectTimeout(100000);
        conf3.setReadTimeout(200000);
        conf3.setWriteTimeout(10000);
        set.add(conf3);
        Assert.assertEquals(set.size(), 2);
    }


}
