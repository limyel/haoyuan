package com.limyel.haoyuan.module.product.api.sku;

import com.limyel.haoyuan.module.product.api.sku.vo.SkuVO;
import com.limyel.haoyuan.module.product.constant.ApiConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(ApiConstant.NAME)
public interface ProductSkuFacade {

    @GetMapping("/product/sku/{id}")
    SkuVO get(@PathVariable("id") Long id);

}
