package com.limyel.haoyuan.mall.product.api;

import com.limyel.haoyuan.common.cloud.config.FeignDecoderConfig;
import com.limyel.haoyuan.mall.product.dto.SpuRDTO;
import com.limyel.haoyuan.mall.product.dto.StockDeductRDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "mall-product", contextId = "spu", configuration = FeignDecoderConfig.class)
public interface SpuApi {

    @GetMapping("/product/rpc/spu/get/by-id/{id}")
    SpuRDTO getById(@PathVariable("id") Long id);

    @GetMapping("/product/rpc/spu/get/by-ids")
    List<SpuRDTO> getByIds(@RequestParam("ids") List<Long> ids);

    @PostMapping("/product/rpc/spu/deduct")
    void deduct(StockDeductRDTO dto);

}
