package com.limyel.haoyuan.blog.store.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.common.mybatis.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 创建订单
 * 支付订单
 * 关闭订单
 * 查看订单
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("store_order")
public class OrderEntity extends BaseEntity {

    /**
     * 订单编号
     */
    private String sn;

    /**
     * 订单总额（分）
     */
    private Long totalAmount;

    /**
     * 商品总数
     */
    private Integer totalQuantity;

    /**
     * 订单状态，101-待支付，102-用户取消，103-系统取消, 201-已完成
     */
    private Integer status;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 应付总额（分）
     */
    private Long paymentAmount;

    /**
     * 支付时间
     */
    private LocalDateTime paymentTime;

}
