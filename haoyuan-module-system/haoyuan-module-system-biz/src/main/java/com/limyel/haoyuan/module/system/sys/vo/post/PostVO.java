package com.limyel.haoyuan.module.system.sys.vo.post;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostVO {

    private Long id;

    private String name;

    private String code;

    private Integer status;

    private String remark;

    private LocalDateTime createTime;

}
