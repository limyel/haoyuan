package com.limyel.haoyuan.cloud.mall.trade.vo.order;

import com.limyel.haoyuan.cloud.mall.trade.dto.order.OrderItemDTO;
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

    private List<OrderItemDTO> orderItems;

}
