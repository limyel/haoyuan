package com.limyel.haoyuan.mall.common.trade.dto.order;

import lombok.Data;

import java.util.List;

@Data
public class OrderSubmitDTO {

    private String orderSn;

    private String orderToken;

    private List<OrderItemDTO> orderItems;

    private Long paymentAmount;

    private String remark;

}
