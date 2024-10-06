package com.limyel.haoyuan.mall.common.trade.dto.userspu;

import com.limyel.haoyuan.mall.common.trade.entity.OrderItemEntity;
import lombok.Data;

import java.util.List;

@Data
public class UserSpuDTO {

    private Long userId;

    List<OrderItemEntity> orderItems;

}
