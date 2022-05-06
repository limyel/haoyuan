package com.limyel.haoyuan.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableAutoConfiguration
@PropertySource(value = {"classpath:application-admin.properties"})
@ComponentScan(basePackageClasses = {HaoyuanAdminApplication.class})
public class HaoyuanAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(HaoyuanAdminApplication.class, args);
    }
}