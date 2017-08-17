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
package org.onap.msb.sdk.httpclient.metric;

import static com.codahale.metrics.MetricRegistry.name;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.codahale.metrics.JmxReporter;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Slf4jReporter;
import com.codahale.metrics.Timer;

/**
 * @author hu.rui
 *
 */
public class MetricManager {

  private static MetricRegistry registry = new MetricRegistry();

  private static Slf4jReporter slf4jReporter;

  private static JmxReporter jmxReporter;

  static {
    initMetricReporters();
  }

  protected Map<Method, Timer> timers = new HashMap<>();
  protected Map<Method, Meter> meters = new HashMap<>();
  protected Map<Method, Meter> exceptionmeters = new HashMap<>();



  private static void initMetricReporters() {

    /*
     * slf4jReporter = Slf4jReporter.forRegistry(registry)
     * .outputTo(LoggerFactory.getLogger(RetrofitServiceHandler.class))
     * .convertRatesTo(TimeUnit.SECONDS) .convertDurationsTo(TimeUnit.MILLISECONDS) .build();
     * slf4jReporter.start(10, TimeUnit.MINUTES);
     */

    jmxReporter = JmxReporter.forRegistry(registry).build();
    jmxReporter.start();

  }

  public void initMetric(Class retrofitSrvInterfaceClazz) {

    for (Method method : retrofitSrvInterfaceClazz.getMethods()) {

      timers.put(method, registry.timer(chooseName(method, "timer")));
      meters.put(method, registry.meter(chooseName(method, "meter")));
      exceptionmeters.put(method, registry.meter(chooseName(method, "exception")));
    }


  }

  private String chooseName(final Method method, final String... suffixes) {
    return name(name(method.getDeclaringClass(), method.getName()), suffixes);
  }

  public MetricObject getMetricObject(Method method) {
    return new MetricObject(timers.get(method), meters.get(method), exceptionmeters.get(method));
  }

}
