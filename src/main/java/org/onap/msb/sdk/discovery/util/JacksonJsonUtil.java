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
 * Copyright (C) 2015 ZTE, Inc. and others. All rights reserved. (ZTE)
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

import org.onap.msb.sdk.discovery.common.RouteException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonJsonUtil {

    private static final Logger logger = LoggerFactory.getLogger(JacksonJsonUtil.class);

    private static ObjectMapper mapper;

    /**
     *
     * @param createNew create a new instanse if truer, otherwise return the existing instance
     * @return
     */
    public static synchronized ObjectMapper getMapperInstance() {
        if (mapper == null) {
            mapper = new ObjectMapper();
        }
        return mapper;
    }

    /**
     * conver java object to json
     *
     * @param obj
     * @return
     * @throws Exception
     */
    public static String beanToJson(Object obj) throws RouteException {
        String json = null;
        try {
            ObjectMapper objectMapper = getMapperInstance();
            json = objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throwsRouteException("Class beanToJson faild", e, "BEAN_TO_JSON_FAILD");
        }
        return json;
    }



    /**
     * convert json string to java object
     *
     * @param json
     * @param cls
     * @return
     * @throws Exception
     */
    public static Object jsonToBean(String json, Class<?> cls) throws RouteException {
        Object vo = null;
        try {
            ObjectMapper objectMapper = getMapperInstance();


            vo = objectMapper.readValue(json, cls);

        } catch (Exception e) {
            throwsRouteException(cls + " JsonTobean faild:" + e.getMessage(), e, "JSON_TO_BEAN_FAILD");
        }
        return vo;
    }


    private static void throwsRouteException(String errorMsg, Exception e, String errorCode) throws RouteException {
        String msg = errorMsg + ".errorMsg:" + e.getMessage();
        logger.error(msg);
        throw new RouteException(errorMsg, errorCode);
    }
}
