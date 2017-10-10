/*******************************************************************************
 * Copyright 2017 ZTE, Inc. and others.
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
 ******************************************************************************/
/**
 * 
 */
package org.onap.msb.sdk.httpclient.handler;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.onap.msb.sdk.httpclient.annotaion.LoadBalance.LBSTYLE;
import org.onap.msb.sdk.httpclient.conf.HttpClientConf;
import org.onap.msb.sdk.httpclient.convert.jackson.JacksonConverterFactoryBuilder;
import org.onap.msb.sdk.httpclient.handler.impl.ConnectionParamsBuilder;
import org.onap.msb.sdk.httpclient.handler.impl.ConverterFactoryBuilder;
import org.onap.msb.sdk.httpclient.handler.impl.LBBuilder;
import org.onap.msb.sdk.httpclient.handler.impl.MetricmanagerBuilder;
import org.onap.msb.sdk.httpclient.handler.impl.RetrofitHandlerContextBuilder;
import org.onap.msb.sdk.httpclient.handler.impl.ServiceHttpEndPointBeanObjectBuilder;
import org.onap.msb.sdk.httpclient.lb.RoundRobinLBStrategy;

import com.google.common.collect.Lists;


public class ServiceBuilderTest {

  private List<HandlerContextBuilder> builders = Lists.newArrayList();
  private HttpClientConf globalHttpClientConf;
  
  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
    

    globalHttpClientConf = new HttpClientConf();
    RetrofitServiceHandlerContext.setGlobalHttpClientConf(globalHttpClientConf);
    init();
    
  }

  @Test
  public void test_buildDefaultHandlerContext() {
    
   
    RetrofitServiceHandlerContext ctx = buildCtx(AnimalServiceClient1.class);
    
   assertEquals(RoundRobinLBStrategy.class,ctx.getLbStrategy().getClass());
    assertEquals(LBSTYLE.CLIENT, ctx.getLbStyle());
    assertEquals("animals",ctx.getServiceHttpEndPointBeanObject().getServiceName());
    assertEquals("v1",ctx.getServiceHttpEndPointBeanObject().getServiceVersion());
    
    
    
  }
  
  
  @Test
  public void test_buildCustomizationHandlerContext() {
    
   
    RetrofitServiceHandlerContext ctx = buildCtx(AnimalServiceClient2.class);
    
    assertEquals(RoundRobinLBStrategy.class,ctx.getLbStrategy().getClass());
    assertEquals(LBSTYLE.MSB, ctx.getLbStyle());
    assertEquals("animals",ctx.getServiceHttpEndPointBeanObject().getServiceName());
    assertEquals("v1",ctx.getServiceHttpEndPointBeanObject().getServiceVersion());
    assertEquals(JacksonConverterFactoryBuilder.class,ctx.getConverterFactoryBuilder().getClass());
    assertEquals(1000,ctx.getHttpClientConf().getReadTimeout());
    assertEquals(2000,ctx.getHttpClientConf().getConnectTimeout());
    assertEquals(3000,ctx.getHttpClientConf().getWriteTimeout());
  }
  
  
  private RetrofitServiceHandlerContext buildCtx(Class<?> retrofitSrvInterfaceClazz){
    RetrofitServiceHandlerContext ctx = new RetrofitServiceHandlerContext();
    ctx.setRetrofitSrvInterfaceClazz(retrofitSrvInterfaceClazz);
    for (HandlerContextBuilder builder : builders) {
      builder.build(ctx);
    }
   
    return ctx;
  }
  
  
  private void init() {
    builders.add(new ServiceHttpEndPointBeanObjectBuilder());
    builders.add(new MetricmanagerBuilder());
    builders.add(new LBBuilder());
    builders.add(new ConnectionParamsBuilder());
    builders.add(new ConverterFactoryBuilder());
    builders.add(new RetrofitHandlerContextBuilder());
  }
}
