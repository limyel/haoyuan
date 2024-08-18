package com.limyel.haoyuan.common.security.config;

import com.limyel.haoyuan.common.core.util.JwtUtil;
import com.limyel.haoyuan.common.security.filter.TokenAuthenticationFilter;
import com.limyel.haoyuan.common.security.handler.AccessDeniedHandlerImpl;
import com.limyel.haoyuan.common.security.handler.AuthenticationEntryPointImpl;
import com.limyel.haoyuan.common.security.handler.AuthenticationFailureHandlerImpl;
import com.limyel.haoyuan.common.security.handler.AuthenticationSuccessHandlerImpl;
import com.limyel.haoyuan.common.security.token.JwtTokenHelper;
import com.limyel.haoyuan.common.security.token.TokenHelper;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

@AutoConfiguration
@EnableConfigurationProperties(SecurityProperties.class)
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private UserDetailsService userDetailsService;

    @Resource
    private SecurityProperties securityProperties;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private JwtUtil jwtUtil;

    @Bean
    public TokenHelper tokenHelper() {
        return new JwtTokenHelper(jwtUtil);
    }

    @Bean
    public AuthenticationSecurityConfig authenticationSecurityConfig() {
        return new AuthenticationSecurityConfig(authenticationSuccessHandler(), authenticationFailureHandler(), passwordEncoder,
                userDetailsService, securityProperties);
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new AuthenticationEntryPointImpl();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new AccessDeniedHandlerImpl();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new AuthenticationSuccessHandlerImpl(tokenHelper());
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new AuthenticationFailureHandlerImpl();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 开启跨域
                .cors()
                .and()
                // 关闭 csrf
                .csrf().disable()
                .formLogin().disable()
                // 用户认证相关配置
                .apply(authenticationSecurityConfig())
                .and()
                    // 不通过 session 获取 SecurityContext
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .authorizeRequests()
                    // /admin/** 的接口需要认证鉴权，其余接口可自由访问
                    .antMatchers("/sys/**").authenticated()
                    .anyRequest().permitAll()
                .and()
                    // 处理未认证异常
                    .httpBasic().authenticationEntryPoint(authenticationEntryPoint())
                .and()
                    // 处理未授权异常
                    .exceptionHandling().accessDeniedHandler(accessDeniedHandler())
                .and()
                    .addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * Token 校验过滤器
     * @return
     */
    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() {
        return new TokenAuthenticationFilter(tokenHelper(), userDetailsService, authenticationEntryPoint(), securityProperties);
    }

}
