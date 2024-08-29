package com.limyel.haoyuan.mall.trade;

import com.limyel.haoyuan.mall.member.UserApi;
import com.limyel.haoyuan.mall.product.api.SpuApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackageClasses = {SpuApi.class, UserApi.class})
public class TradeApplication {
    public static void main(String[] args) {
        SpringApplication.run(TradeApplication.class, args);
    }
}
