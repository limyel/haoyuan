package com.limyel.haoyuan.module.member.config;

import com.limyel.haoyuan.module.product.api.sku.ProductSkuFacade;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@EnableFeignClients(clients = {ProductSkuFacade.class})
public class RpcConfig {
}
