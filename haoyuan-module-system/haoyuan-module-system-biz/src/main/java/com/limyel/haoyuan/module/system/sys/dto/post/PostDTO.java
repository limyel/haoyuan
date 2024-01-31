package com.limyel.haoyuan.module.system.sys.dto.post;

import lombok.Data;

@Data
public class PostDTO {

    private Long id;

    /**
     * 岗位编码
     */
    private String code;

    /**
     * 岗位名称
     */
    private String name;

    /**
     * 显示顺序
     */
    private Integer sort;

    /**
     * 岗位状态
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

}
