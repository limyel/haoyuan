package com.limyel.haoyuan.cloud.mall.trade.dto.order;

import lombok.Data;

@Data
public class OrderItemDTO {

    private String spuName;

    private Long skuId;

    private String skuName;

    private Long price;

    private Integer quantity;

    private Integer type;

}
