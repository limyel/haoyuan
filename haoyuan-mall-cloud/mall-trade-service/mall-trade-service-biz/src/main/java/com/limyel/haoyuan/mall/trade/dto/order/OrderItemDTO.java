package com.limyel.haoyuan.mall.trade.dto.order;

import lombok.Data;

@Data
public class OrderItemDTO {

    private Long spuId;

    private String spuName;

    private String picUrl;

    private Long price;

    private Integer quantity;

}
