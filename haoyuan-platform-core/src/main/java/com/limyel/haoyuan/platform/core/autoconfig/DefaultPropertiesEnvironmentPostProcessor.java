package com.limyel.haoyuan.platform.core.autoconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * 对配置进行后置处理。
 * EnvironmentPostProcessor 在 spring.factories 中注册。
 */
public class DefaultPropertiesEnvironmentPostProcessor implements EnvironmentPostProcessor {
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        Map<String, Object> source = new HashMap<>();
        source.put("spring.main.banner-mode", "off");
        MapPropertySource propertySource = new MapPropertySource("haoyuan-platform-propertysource", source);
        environment.getPropertySources().addLast(propertySource);
    }
}
