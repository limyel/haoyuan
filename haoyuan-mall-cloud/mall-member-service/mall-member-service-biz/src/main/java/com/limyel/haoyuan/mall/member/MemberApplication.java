package com.limyel.haoyuan.mall.member;

import com.limyel.haoyuan.common.satoken.service.StpUserUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MemberApplication {
    public static void main(String[] args) {
        System.out.println(StpUserUtil.TYPE);
        SpringApplication.run(MemberApplication.class, args);
    }
}
