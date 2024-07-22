package com.limyel.haoyuan.platform.core.util.spring;

import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class EnvironmentHolder {

    private static boolean locked = false;
    private static ConfigurableEnvironment environment;

    public static synchronized void setEnvironment(ConfigurableEnvironment environment) {
        if (locked) {
            return;
        }
        EnvironmentHolder.environment = environment;
        locked = true;
    }

    public static String getProperty(String key) {
        return environment.getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        return environment.getProperty(key, defaultValue);
    }

    public static <T> T getProperty(String key, Class<T> targetClass) {
        return environment.getProperty(key, targetClass);
    }

    public static <T> T getProperty(String key, Class<T> targetClass, T defaultValue) {
        return environment.getProperty(key, targetClass, defaultValue);
    }

    public static Map<String, Object> getPropertyByPrefix(String prefix) {
        if (environment == null || !StringUtils.hasText(prefix)) {
            return new HashMap<>();
        }
        Map<String, Object> property = new HashMap<>();
        MutablePropertySources propertySources = environment.getPropertySources();
        for (PropertySource<?> propertySource : propertySources) {
            if (propertySource instanceof MapPropertySource mps) {
                Set<Map.Entry<String, Object>> entrySet = mps.getSource().entrySet();
                for (Map.Entry<String, Object> entry : entrySet) {
                    if (entry.getKey().startsWith(prefix)) {
                        property.put(entry.getKey(), entry.getValue());
                    }
                }
            }
        }
        return property;
    }

    public static Environment getEnvironment() {
        return environment;
    }

    public static ConfigurableEnvironment getConfigurableEnvironment() {
        return environment;
    }

}
