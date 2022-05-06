package com.limyel.haoyuan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.limyel.haoyuan.portal.dao")
public class HaoyuanApplication {
    public static void main(String[] args) {
        SpringApplication.run(HaoyuanApplication.class, args);
    }
}
