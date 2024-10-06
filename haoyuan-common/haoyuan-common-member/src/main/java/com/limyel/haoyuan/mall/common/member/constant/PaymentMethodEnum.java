package com.limyel.haoyuan.mall.common.member.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentMethodEnum {
    POINT(0, "积分"),
    BALANCE(1, "余额"),
    POINT_BALANCE(2, "积分+余额")
    ;

    private final Integer value;
    private final String label;
}
