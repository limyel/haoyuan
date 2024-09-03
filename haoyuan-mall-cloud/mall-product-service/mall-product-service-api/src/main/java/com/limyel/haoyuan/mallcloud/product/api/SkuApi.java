package com.limyel.haoyuan.mallcloud.product.api;

import com.limyel.haoyuan.common.cloud.config.FeignDecoderConfig;
import com.limyel.haoyuan.mallcloud.product.dto.SkuConfirm;
import com.limyel.haoyuan.mallcloud.product.dto.StockDeduct;
import com.limyel.haoyuan.mallcloud.product.dto.StockReturn;
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

    @PostMapping("/product/rpc/sku/stock/deduct")
    void deductStock(StockDeduct dto);

    @PostMapping("/product/rpc/sku/stock/return")
    void returnStock(StockReturn dto);

}
