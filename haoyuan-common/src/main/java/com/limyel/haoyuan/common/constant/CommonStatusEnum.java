package com.limyel.haoyuan.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommonStatusEnum {
    DISABLE(0, "关闭"),
    ENABLE(1, "开启")
    ;

    private final Integer status;
    private final String name;

}
