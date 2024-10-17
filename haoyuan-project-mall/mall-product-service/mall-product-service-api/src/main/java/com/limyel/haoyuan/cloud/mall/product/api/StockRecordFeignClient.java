package com.limyel.haoyuan.cloud.mall.product.api;

import com.limyel.haoyuan.cloud.mall.product.dto.StockRecotdExist;
import com.limyel.haoyuan.common.cloud.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "mall-product", contextId = "stockRecord", configuration = FeignConfig.class)
public interface StockRecordFeignClient {

    @GetMapping("/product/rpc/stock-record/exist/{orderSn}")
    List<StockRecotdExist> checkExist(@PathVariable("orderSn") String orderSn);

}
