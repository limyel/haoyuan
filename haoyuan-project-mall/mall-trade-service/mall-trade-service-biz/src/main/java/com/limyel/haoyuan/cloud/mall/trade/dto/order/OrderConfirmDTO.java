package com.limyel.haoyuan.cloud.mall.trade.dto.order;

import lombok.Data;

import java.util.List;

@Data
public class OrderConfirmDTO {

    private List<CartItem> list;

    @Data
    public static class CartItem {
        private Long skuId;
        private Integer quantity;
    }

}
