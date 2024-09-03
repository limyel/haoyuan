package com.limyel.haoyuan.mallcloud.product.vo.stockrule;

import lombok.Data;

@Data
public class StockRulePageVO {

    private Long id;

    private Long spuId;

    private String spuName;

    private Integer type;

    private Integer quantity;

    private Integer minQuantity;

    private Integer maxQuantity;

    private String cron;

    private Integer status;

}
