package com.limyel.haoyuan.coupon.template.api.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum CouponType {

    UNKNOWN("unknown", 0),
    MONEY_OFF("满减券", 1),
    DISCOUNT("打折", 2),
    RANDOM_DISCOUNT("随机减", 3),
    LONELY_NIGHT_MONEY_OFF("晚间双倍优惠券", 4)
    ;

    private final String description;
    private final Integer code;

    public static CouponType convert(Integer code) {
        return Stream.of(values())
                .filter(bean -> bean.code.equals(code))
                .findFirst()
                .orElse(UNKNOWN);
    }

}
