package com.limyel.haoyuan.framework.security.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
@AutoConfiguration
public class HaoyuanSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin();
        http.authorizeRequests().antMatchers("/sys/**").hasRole("admin").anyRequest().authenticated();
        http.userDetailsService(userDetailsService());
    }

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager userManager = new InMemoryUserDetailsManager();
        userManager.createUser(User.withUsername("ZhangSan").password(passwordEncoder().encode("123456")).roles("admin").build());
        userManager.createUser(User.withUsername("LiSi").password(passwordEncoder().encode("654321")).roles("user").build());
        userManager.createUser(User.withUsername("WangWu").password(passwordEncoder().encode("123456")).roles("admin", "manager").build());
        return userManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
