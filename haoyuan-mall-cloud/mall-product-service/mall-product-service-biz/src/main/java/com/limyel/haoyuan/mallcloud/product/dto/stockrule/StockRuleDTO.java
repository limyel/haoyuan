package com.limyel.haoyuan.mallcloud.product.dto.stockrule;

import lombok.Data;

@Data
public class StockRuleDTO {

    private Long spuId;

    private Integer type;

    private Integer quantity;

    private Integer minQuantity;

    private Integer maxQuantity;

    private String cron;

    private Integer status;

}
