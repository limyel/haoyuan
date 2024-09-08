package com.limyel.haoyuan.mall.security.config;

import com.limyel.haoyuan.mall.security.service.SysUserDetailsService;
import com.limyel.haoyuan.mall.sys.api.SysUserApi;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 认证授权中，与用户管理相关的配置
 */
@Configuration
@RequiredArgsConstructor
public class UserManagerConfig {

    private final SysUserApi sysUserApi;

    @Bean
    public UserDetailsService userDetailsService() {
        return new SysUserDetailsService(sysUserApi);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
