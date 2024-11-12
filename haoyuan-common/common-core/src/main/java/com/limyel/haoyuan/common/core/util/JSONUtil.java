package com.limyel.haoyuan.common.core.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.json.JsonParseException;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * JSON 工具类
 */
public class JSONUtil {

    public static final ObjectMapper OBJECT_MAPPER;

    static {
        OBJECT_MAPPER = SpringContextUtil.getBean(ObjectMapper.class);
    }

    public static Map<String, Object> parseObject(String json) {
        if (StringUtils.hasText(json)) {
            return parseObject(json, Map.class);
        }
        return new HashMap<>();
    }

    public static <T> T parseObject(String json, Class<T> type) {
        try {
            return OBJECT_MAPPER.readValue(json, type);
        } catch (IOException e) {
            throw new JsonParseException(e);
        }
    }

    public static String toJson(Object obj) {
        if (obj == null) {
            return "";
        }
        try {
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new JsonParseException(e);
        }
    }

}
