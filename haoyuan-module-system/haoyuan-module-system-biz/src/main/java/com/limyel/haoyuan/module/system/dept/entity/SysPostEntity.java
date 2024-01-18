package com.limyel.haoyuan.module.system.dept.entity;

import com.limyel.haoyuan.framework.mybatis.pojo.TenantBaseEntity;
import lombok.Data;

@Data
public class SysPostEntity extends TenantBaseEntity {

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
