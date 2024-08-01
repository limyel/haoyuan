package com.limyel.haoyuan.member.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PointTypeEnum {
    REDUCE(0, "减少"),
    ADD(1, "增加")
    ;

    private final Integer value;
    private final String name;

}
