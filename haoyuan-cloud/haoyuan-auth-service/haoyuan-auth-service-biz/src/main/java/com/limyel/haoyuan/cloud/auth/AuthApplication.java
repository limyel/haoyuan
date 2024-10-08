package com.limyel.haoyuan.cloud.auth;

import com.limyel.haoyuan.mall.member.api.UserApi;
import com.limyel.haoyuan.mall.sys.api.SysUserFeignClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackageClasses = {SysUserFeignClient.class, UserApi.class})
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}
