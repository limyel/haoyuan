package com.limyel.haoyuan.mall.common.product.dto.api;

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
