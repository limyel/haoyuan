package com.limyel.haoyuan.mall.trade.vo.order;

import lombok.Data;

import java.util.List;

@Data
public class OrderListVO {

    private Long id;

    private String orderSn;

    private Long totalAmount;

    private Integer totalQuantity;

    private Long paymentAmount;

    private Integer status;

    private String remark;

    private List<OrderItemVO> orderItems;

}
