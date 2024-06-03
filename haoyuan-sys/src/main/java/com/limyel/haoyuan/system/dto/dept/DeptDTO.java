package com.limyel.haoyuan.system.dto.dept;

import lombok.Data;

@Data
public class DeptDTO {

    private Long id;

    private String name;

    private Long pid;

    private Integer sort;

    private Long leaderId;

    private String mobile;

    private String email;

    private Integer status;

}