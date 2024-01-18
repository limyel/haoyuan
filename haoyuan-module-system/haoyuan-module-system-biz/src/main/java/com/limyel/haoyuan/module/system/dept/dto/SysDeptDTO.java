package com.limyel.haoyuan.module.system.dept.dto;

import lombok.Data;

@Data
public class SysDeptDTO {

    private Long id;

    private String name;

    private Long pid;

    private Integer sort;

    private Long leaderId;

    private String mobile;

    private String email;

    private Integer status;

}
