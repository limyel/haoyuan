package com.limyel.haoyuan.mall.product.dto;

import lombok.Data;

import java.util.List;

@Data
public class StockDeductRDTO {

    private String orderToken;

    List<SkuDTO> skuList;

    @Data
    public static class SkuDTO {
        private Long skuId;

        private Integer quantity;
    }

}
