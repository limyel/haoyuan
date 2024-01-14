package com.limyel.haoyuan.module.product.api.sku;

import com.limyel.haoyuan.module.product.api.sku.vo.SkuVO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductSkuApi implements ProductSkuFacade {

    @Override
    public SkuVO get(@PathVariable("id") Long id) {
        SkuVO result = new SkuVO();
        result.setId(id);
        return result;
    }

}
