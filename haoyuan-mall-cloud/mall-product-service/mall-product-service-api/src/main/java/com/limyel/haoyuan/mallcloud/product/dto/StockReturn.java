package com.limyel.haoyuan.mallcloud.product.dto;

import lombok.Data;

import java.util.List;

@Data
public class StockReturn {

    private List<Sku> list;

    @Data
    public static class Sku {

        private Long skuId;

        private Integer quantity;

    }

}
