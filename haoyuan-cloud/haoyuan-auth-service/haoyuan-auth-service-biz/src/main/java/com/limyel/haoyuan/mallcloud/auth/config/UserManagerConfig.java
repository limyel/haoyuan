package com.limyel.haoyuan.mallcloud.auth.config;

import com.limyel.haoyuan.mall.member.api.UserApi;
import com.limyel.haoyuan.mall.sys.api.SysUserFeignClient;
import com.limyel.haoyuan.mallcloud.auth.service.MemberUserDetailsService;
import com.limyel.haoyuan.mallcloud.auth.service.SysUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class UserManagerConfig {

    private final UserApi userApi;

    private final SysUserFeignClient sysUserFeignClient;

    @Bean
    public MemberUserDetailsService memberUserDetailsService() {
        return new MemberUserDetailsService(userApi);
    }

    @Bean
    public SysUserDetailsService sysUserDetailsService() {
        return new SysUserDetailsService(sysUserFeignClient);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
