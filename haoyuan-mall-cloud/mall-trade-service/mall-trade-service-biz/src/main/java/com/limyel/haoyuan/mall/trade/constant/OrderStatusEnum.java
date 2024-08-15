package com.limyel.haoyuan.mall.trade.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatusEnum {
    UNPAID(0, "待支付"),
    CANCELED(1, "已取消"),
    COMPLETE(2, "已完成")
    ;

    private final Integer value;
    private final String name;
}
