package com.limyel.haoyuan.mall.trade.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.common.mybatis.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("trade_order")
public class OrderEntity extends BaseEntity {

    /**
     * 订单编号
     */
    private String orderSn;

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
     * 备注
     */
    private String remark;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 使用的优惠券
     */
    private Long couponId;

    /**
     * 优惠券抵扣金额（分）
     */
    private Long couponAmount;

    /**
     * 应付总额（分）
     */
    private Long paymentAmount;

    /**
     * 支付时间
     */
    private LocalDateTime paymentTime;

}
