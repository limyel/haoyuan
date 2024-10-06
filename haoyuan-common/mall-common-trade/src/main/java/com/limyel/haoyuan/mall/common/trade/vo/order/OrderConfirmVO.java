package com.limyel.haoyuan.mall.common.trade.vo.order;

import com.limyel.haoyuan.mall.common.trade.dto.order.OrderItemDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrderConfirmVO {

    private String orderToken;

    private List<OrderItemDTO> orderItems = new ArrayList<>();

}
