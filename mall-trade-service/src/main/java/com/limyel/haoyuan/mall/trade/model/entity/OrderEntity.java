package com.limyel.haoyuan.mall.trade.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.common.mybatis.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("mall_order")
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
     * 订单状态，0-待支付，1-已取消，2-超时未支付, 3-已完成
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 会员 ID
     */
    private Long memberId;

    /**
     * 优惠券
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

    /**
     * 支付方式，0-积分，1-余额，2-积分+余额
     */
    private Integer paymentMethod;

}
