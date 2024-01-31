package com.limyel.haoyuan.module.system.vo.dept;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SysPostVO {

    private Long id;

    private String name;

    private String code;

    private Integer status;

    private String remark;

    private LocalDateTime createTime;

}
