package com.limyel.haoyuan.cloud.sys.vo.role;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RolePageVO {

    private Long id;

    private String name;

    private String code;

    private Integer sort;

    private Integer status;

    private LocalDateTime createTime;

}
