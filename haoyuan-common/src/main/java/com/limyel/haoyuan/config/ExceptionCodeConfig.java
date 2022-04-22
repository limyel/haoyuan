package com.limyel.haoyuan.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "haoyuan")
@PropertySource(value = "classpath:config/exception-code.properties")
public class ExceptionCodeConfig {

    private Map<Integer, String> codes = new HashMap<>();

    public String getMessage(int code) {
        return this.codes.get(code);
    }
}
