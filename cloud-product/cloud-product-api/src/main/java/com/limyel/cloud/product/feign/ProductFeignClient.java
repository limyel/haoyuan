package com.limyel.cloud.product.feign;

import com.limyel.haoyuan.common.cloud.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "cloud-product", contextId = "product", configuration = FeignConfig.class)
public interface ProductFeignClient {

}
