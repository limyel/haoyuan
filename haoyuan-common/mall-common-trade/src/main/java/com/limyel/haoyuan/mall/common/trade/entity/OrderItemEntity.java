package com.limyel.haoyuan.mall.common.trade.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.common.mybatis.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("trade_order_item")
public class OrderItemEntity extends BaseEntity {

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * SKU ID
     */
    private Long skuId;

    /**
     * SPU 名称
     */
    private String spuName;

    /**
     * SKU 名称
     */
    private String skuName;

    /**
     * 类型，0-一次性，1-订阅
     */
    private Integer type;

    /**
     * 商品单价（分）
     */
    private Long price;

    /**
     * 商品数量
     */
    private Integer quantity;

    /**
     * 商品总价（分）
     */
    private Long totalAmount;

}
