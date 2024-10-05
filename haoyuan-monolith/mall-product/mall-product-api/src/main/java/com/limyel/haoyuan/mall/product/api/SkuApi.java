package com.limyel.haoyuan.mall.product.api;

import com.limyel.haoyuan.mall.common.product.dto.api.SkuConfirm;
import com.limyel.haoyuan.mall.common.product.dto.api.StockDeduct;

import java.util.List;

public interface SkuApi {

    SkuConfirm getById(Long id);

    List<SkuConfirm> getByIds(List<Long> ids);

    void deduct(StockDeduct dto);

}
