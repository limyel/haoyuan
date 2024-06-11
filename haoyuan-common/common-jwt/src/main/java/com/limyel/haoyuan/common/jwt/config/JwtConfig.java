package com.limyel.haoyuan.common.jwt.config;

import com.limyel.haoyuan.common.jwt.util.JwtTokenHelper;
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
    public JwtTokenHelper jwtTokenHelper() {
        return new JwtTokenHelper(properties);
    }

}
