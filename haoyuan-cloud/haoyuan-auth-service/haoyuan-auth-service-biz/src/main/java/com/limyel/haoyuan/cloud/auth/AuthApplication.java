package com.limyel.haoyuan.cloud.auth;

import com.limyel.haoyuan.cloud.member.api.UserFeignClient;
import com.limyel.haoyuan.cloud.sys.api.SysUserFeignClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackageClasses = {SysUserFeignClient.class, UserFeignClient.class})
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}
