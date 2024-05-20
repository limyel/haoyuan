package com.limyel.haoyuan.system.vo.dept;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DeptVO {

    private Long id;

    private String name;

    private Long pid;

    private Integer sort;

    private Long leaderId;

    private String phone;

    private String email;

    private Integer status;

    private LocalDateTime createTime;

}