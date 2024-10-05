package com.limyel.haoyuan.mall.product.controller.rpc;

import com.limyel.haoyuan.mall.common.product.dto.api.SkuConfirm;
import com.limyel.haoyuan.mall.common.product.dto.api.StockDeduct;
import com.limyel.haoyuan.mall.product.api.SkuApi;
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
