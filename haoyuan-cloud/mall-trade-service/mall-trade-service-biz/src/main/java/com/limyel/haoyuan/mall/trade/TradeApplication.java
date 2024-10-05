package com.limyel.haoyuan.mall.trade;

import com.limyel.haoyuan.mall.member.api.UserApi;
import com.limyel.haoyuan.mallcloud.product.api.SkuFeignClient;
import com.limyel.haoyuan.mallcloud.product.api.SpuFeignClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackageClasses = {SpuFeignClient.class, SkuFeignClient.class, UserApi.class})
public class TradeApplication {
    public static void main(String[] args) {
        SpringApplication.run(TradeApplication.class, args);
    }
}
