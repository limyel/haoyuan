package com.limyel.haoyuan.blogcloud.sys.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MenuTypeEnum {
    DIR(1, "目录"),
    MENU(2, "菜单"),
    BUTTON(3, "按钮")
    ;

    private final Integer type;
    private final String name;

}