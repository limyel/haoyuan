package com.limyel.haoyuan.mall.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.httpBasic();

        // 请求认证鉴权配置，从特殊到一般
        http.authorizeRequests(req -> {
            req
                    .antMatchers("/app/user/get/me").authenticated()
                    .anyRequest().permitAll();
        });

        return http.build();
    }

}
