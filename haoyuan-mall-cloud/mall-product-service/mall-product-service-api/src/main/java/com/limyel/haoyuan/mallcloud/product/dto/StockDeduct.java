package com.limyel.haoyuan.mallcloud.product.dto;

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
