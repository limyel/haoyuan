package com.limyel.haoyuan.mall.trade.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.common.mybatis.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("mall_order_item")
public class OrderItemEntity extends BaseEntity {

    /**
     * 订单 ID
     */
    private Long orderId;

    /**
     * 商品 ID
     */
    private Long productId;

    /**
     * 商品名称
     */
    private String productName;

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
