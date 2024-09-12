package com.limyel.haoyuan.mallcloud.product.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StockRecotdExist {

    private Long skuId;

    private Integer quantity;

    private Integer skuNum;

    private String orderSn;

    private LocalDateTime createTime;

}
