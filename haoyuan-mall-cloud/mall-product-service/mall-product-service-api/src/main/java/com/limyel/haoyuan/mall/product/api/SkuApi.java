package com.limyel.haoyuan.mall.product.api;

import com.limyel.haoyuan.common.cloud.config.FeignDecoderConfig;
import com.limyel.haoyuan.mall.product.dto.SkuConfirm;
import com.limyel.haoyuan.mall.product.dto.StockDeductRDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "mall-product", contextId = "sku", configuration = FeignDecoderConfig.class)
public interface SkuApi {

    @GetMapping("/product/rpc/sku/get/by-id/{id}")
    SkuConfirm getById(@PathVariable("id") Long id);

    @GetMapping("/product/rpc/sku/get/by-ids")
    List<SkuConfirm> getByIds(@RequestParam("ids") List<Long> ids);

    @PostMapping("/product/rpc/sku/deduct")
    void deduct(StockDeductRDTO dto);

}
