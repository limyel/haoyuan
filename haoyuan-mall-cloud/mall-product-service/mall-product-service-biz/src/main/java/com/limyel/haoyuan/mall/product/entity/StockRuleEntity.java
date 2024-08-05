package com.limyel.haoyuan.mall.product.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.common.mybatis.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("product_stock_rule")
public class StockRuleEntity extends BaseEntity {

    /**
     * 商品ID
     */
    private Long spuId;

    /**
     * 更新类型，0-手动更新，1-定时定量更新，2-定时不定量更新
     */
    private Integer type;

    /**
     * 更新数量
     */
    private Integer quantity;

    /**
     * 最少更新数量
     */
    private Integer minQuantity;

    /**
     * 最多更新数量
     */
    private Integer maxQuantity;

    /**
     * 更新 cron 表达式
     */
    private String cron;

}
