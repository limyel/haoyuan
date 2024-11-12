package com.limyel.haoyuan.common.core.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusEnum {
    DISABLE(0, "非正常"),
    ENABLE(1, "正常")
    ;

    private final Integer value;
    private final String name;

}