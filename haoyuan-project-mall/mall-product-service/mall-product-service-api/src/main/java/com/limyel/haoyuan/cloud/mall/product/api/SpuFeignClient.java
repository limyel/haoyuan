package com.limyel.haoyuan.cloud.mall.product.api;

import com.limyel.haoyuan.cloud.mall.product.dto.SkuConfirm;
import com.limyel.haoyuan.common.cloud.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "mall-product", contextId = "spu", configuration = FeignConfig.class)
public interface SpuFeignClient {

    @GetMapping("/product/rpc/spu/get/by-id/{id}")
    SkuConfirm getById(@PathVariable("id") Long id);

    @GetMapping("/product/rpc/spu/get/by-ids")
    List<SkuConfirm> getByIds(@RequestParam("ids") List<Long> ids);

}
