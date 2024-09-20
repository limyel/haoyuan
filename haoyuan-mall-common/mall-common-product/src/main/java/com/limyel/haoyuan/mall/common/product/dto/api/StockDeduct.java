package com.limyel.haoyuan.mall.common.product.dto.api;

import lombok.Data;

import java.util.List;

@Data
public class StockDeduct {

    private String orderSn;

    List<SkuDTO> skuList;

    @Data
    public static class SkuDTO {
        private Long skuId;

        private Integer quantity;
    }

}
