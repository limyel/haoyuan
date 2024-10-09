package com.limyel.haoyuan.cloud.mall.trade;

import com.limyel.haoyuan.cloud.mall.product.api.SkuFeignClient;
import com.limyel.haoyuan.cloud.mall.product.api.SpuFeignClient;
import com.limyel.haoyuan.cloud.member.api.UserFeignClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackageClasses = {SpuFeignClient.class, SkuFeignClient.class, UserFeignClient.class})
public class TradeApplication {
    public static void main(String[] args) {
        SpringApplication.run(TradeApplication.class, args);
    }
}
