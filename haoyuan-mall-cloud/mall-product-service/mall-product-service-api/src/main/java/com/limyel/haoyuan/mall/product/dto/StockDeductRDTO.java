package com.limyel.haoyuan.mall.product.dto;

import lombok.Data;

import java.util.List;

@Data
public class StockDeductRDTO {

    private String orderToken;

    List<SpuDTO> spuList;

    @Data
    public static class SpuDTO {
        private Long spuId;

        private Integer quantity;
    }

}
