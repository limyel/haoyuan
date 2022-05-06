package com.limyel.haoyuan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
@MapperScan(basePackages = "com.limyel.haoyuan.dao")
public class HaoyuanMpApplication {
    public static void main(String[] args) {
        SpringApplication.run(HaoyuanMpApplication.class, args);
    }
}
