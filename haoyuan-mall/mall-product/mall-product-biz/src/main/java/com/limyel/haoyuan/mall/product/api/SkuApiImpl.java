package com.limyel.haoyuan.mall.product.api;

import com.limyel.haoyuan.mall.product.dto.SkuConfirm;
import com.limyel.haoyuan.mall.product.dto.StockDeduct;
import com.limyel.haoyuan.mall.product.service.SkuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SkuApiImpl implements SkuApi {

    private final SkuService skuService;

    @Override
    public SkuConfirm getById(Long id) {
        SkuConfirm result = skuService.getConfirm(id);
        return result;
    }

    @Override
    public List<SkuConfirm> getByIds(List<Long> ids) {
        List<SkuConfirm> result = skuService.getByIds(ids);
        return result;
    }

    @Override
    public void deduct(StockDeduct dto) {
        skuService.deductStock(dto);
    }

}
