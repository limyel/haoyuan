package com.limyel.haoyuan.common.core.config;

import com.limyel.haoyuan.common.core.config.properties.JwtProperties;
import com.limyel.haoyuan.common.core.util.JwtUtil;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

@AutoConfiguration
@EnableConfigurationProperties(JwtProperties.class)
public class JwtConfig {

    @Resource
    private JwtProperties properties;

    @Bean
    public JwtUtil jwtUtil() {
        return new JwtUtil(properties);
    }

}
