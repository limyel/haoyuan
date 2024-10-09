package com.limyel.haoyuan.cloud.mall.product.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SpuTypeEnum {
    ONCE(0, "一次性"),
    SUBSCRIBE(1, "订阅制")
    ;

    private final Integer value;
    private final String label;

}
