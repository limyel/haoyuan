package com.limyel.haoyuan.blog.admin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationSecurityConfig jwtAuthenticationSecurityConfig;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 关闭 csrf
                .csrf().disable()
                .formLogin().disable()
                // 用户认证相关配置
                .apply(jwtAuthenticationSecurityConfig)
                .and()
                    // 不通过 session 获取 SecurityContext
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .authorizeRequests()
                    // /admin/** 的接口需要认证鉴权，其余接口可自由访问
                    .antMatchers("/admin/**").authenticated()
                    .anyRequest().permitAll()
                .and()
                    // 处理未认证异常
                    .httpBasic().authenticationEntryPoint(authenticationEntryPoint)
                .and()
                    // 处理未授权异常
                    .exceptionHandling().accessDeniedHandler(accessDeniedHandler)
                .and()
                    .addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * Token 校验过滤器
     * @return
     */
    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() {
        return new TokenAuthenticationFilter();
    }

}
