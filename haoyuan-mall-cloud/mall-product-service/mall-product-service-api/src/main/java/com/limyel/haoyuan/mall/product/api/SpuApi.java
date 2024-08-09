package com.limyel.haoyuan.mall.product.api;

import com.limyel.haoyuan.common.cloud.config.FeignDecoderConfig;
import com.limyel.haoyuan.mall.product.dto.SpuRpcDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "mall-product", contextId = "spu", configuration = FeignDecoderConfig.class)
public interface SpuApi {

    @GetMapping("/product/rpc/spu/get/by-id/{id}")
    SpuRpcDTO getById(@PathVariable("id") Long id);

}
