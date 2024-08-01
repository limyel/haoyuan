package com.limyel.haoyuan.blog.store.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.common.mybatis.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 增删查
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("store_order_item")
public class OrderItemEntity extends BaseEntity {

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 商品ID
     */
    private Long productId;

    /**
     * 单价
     */
    private Long price;

    /**
     * 数量
     */
    private Integer quantity;

    /**
     * 商品总价（分）
     */
    private Long totalAmount;

}
