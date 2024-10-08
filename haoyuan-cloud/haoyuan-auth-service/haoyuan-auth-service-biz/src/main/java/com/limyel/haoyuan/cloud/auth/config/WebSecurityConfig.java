package com.limyel.haoyuan.cloud.auth.config;

import com.limyel.haoyuan.cloud.auth.extention.app.AppPasswordAuthenticationProvider;
import com.limyel.haoyuan.cloud.auth.extention.sms.SmsCodeAuthenticationProvider;
import com.limyel.haoyuan.cloud.auth.service.MemberUserDetailsService;
import com.limyel.haoyuan.cloud.auth.service.SysUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final MemberUserDetailsService memberUserDetailsService;

    private final SysUserDetailsService sysUserDetailsService;

    private final PasswordEncoder passwordEncoder;

    /**
     * 认证管理
     * @return
     * @throws Exception
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(sysUserDetailsService)
                .passwordEncoder(passwordEncoder);

        auth.authenticationProvider(smsCodeAuthenticationProvider());
        auth.authenticationProvider(appUsernamePasswordAuthenticationProvider());
    }

    /**
     * http 安全配置
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // todo 开发环境下
                .mvcMatchers("/auth/login", "/auth/refresh-token", "/auth/check-token", "/doc.html", "/webjars/js/**", "/webjars/css/**", "/v3/api-docs/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic().disable()
                .cors()
                .and().csrf().disable();
    }

    @Bean
    public SmsCodeAuthenticationProvider smsCodeAuthenticationProvider() {
        SmsCodeAuthenticationProvider provider = new SmsCodeAuthenticationProvider();
        provider.setMallUserDetailsService(memberUserDetailsService);
        return provider;
    }

    @Bean
    public AppPasswordAuthenticationProvider appUsernamePasswordAuthenticationProvider() {
        AppPasswordAuthenticationProvider provider = new AppPasswordAuthenticationProvider();
        provider.setMemberUserDetailsService(memberUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

}
