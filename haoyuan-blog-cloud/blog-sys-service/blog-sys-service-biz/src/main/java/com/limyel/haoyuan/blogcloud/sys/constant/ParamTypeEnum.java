package com.limyel.haoyuan.blogcloud.sys.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ParamTypeEnum {
    SYSTEM(1, "内置参数"),
    CUSTOM(2, "自定义参数")
    ;

    private final Integer type;
    private final String name;
}