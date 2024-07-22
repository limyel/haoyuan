package com.limyel.haoyuan.platform.core.autoconfig;

import com.limyel.haoyuan.platform.core.enhancer.exception.BasicErrorAttribute;
import com.limyel.haoyuan.platform.core.enhancer.exception.GlobalExceptionHandler;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;

@AutoConfiguration(before = ErrorMvcAutoConfiguration.class)
public class GlobalExceptionHandlerAutoConfig {

    @Bean
    public BasicErrorAttribute basicErrorAttribute() {
        return new BasicErrorAttribute();
    }

    @Bean
    public GlobalExceptionHandler globalExceptionHandler() {
        return new GlobalExceptionHandler();
    }
}
