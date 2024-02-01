package com.limyel.haoyuan.module.system.sys.dto.menu;

import lombok.Data;

@Data
public class MenuDTO {

    private Long id;

    private String name;

    private String permission;

    private Integer type;

    private Integer sort;

    private Long pid;

    private String path;

    private String icon;

    private String component;

    private String componentName;

    private Integer status;

    private Boolean visible;

}
