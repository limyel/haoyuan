package com.limyel.haoyuan.cloud.auth.config;

import com.limyel.haoyuan.cloud.auth.service.MemberUserDetailsService;
import com.limyel.haoyuan.cloud.auth.service.SysUserDetailsService;
import com.limyel.haoyuan.cloud.member.api.UserFeignClient;
import com.limyel.haoyuan.cloud.sys.api.SysUserFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class UserManagerConfig {

    private final UserFeignClient userFeignClient;

    private final SysUserFeignClient sysUserFeignClient;

    @Bean
    public MemberUserDetailsService memberUserDetailsService() {
        return new MemberUserDetailsService(userFeignClient);
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
