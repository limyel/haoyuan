package com.limyel.blog.common.web.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.limyel.blog.common.core.util.SpringContextUtil;

public class JsonUtil {

    private static final ObjectMapper objectMapper;

    static {
        objectMapper = SpringContextUtil.getBean(ObjectMapper.class);
    }

    public static String encode(Object o) {
        try {
            return objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
