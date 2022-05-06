package com.limyel.haoyuan.portal;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableAutoConfiguration
@PropertySource(value = {"classpath:application-portal.properties"})
@ComponentScan(basePackageClasses = {HaoyuanPortalApplication.class})
@MapperScan(basePackages = "com.limyel.haoyuan.portal.dao")
public class HaoyuanPortalApplication {
    public static void main(String[] args) {
        SpringApplication.run(HaoyuanPortalApplication.class, args);
    }
}
