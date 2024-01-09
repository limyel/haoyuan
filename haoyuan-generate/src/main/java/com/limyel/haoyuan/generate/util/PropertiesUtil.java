package com.limyel.haoyuan.generate.util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesUtil {

    private static Properties PROPS = new Properties();
    private static Map<String, String> PROPERTY_MAP = new HashMap<>();

    static {
        try (InputStream is = PropertiesUtil.class.getClassLoader().getResourceAsStream("config.properties")) {
            PROPS.load(is);

            PROPS.forEach((key, value) -> {
                PROPERTY_MAP.put((String) key, (String) value);
            });
        } catch (Exception e) {

        }
    }

    public static String getString(String key) {
        return PROPERTY_MAP.get(key);
    }

}
