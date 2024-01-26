package com.limyel.haoyuan.module.sys.dept.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.framework.mybatis.pojo.TenantBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_dept")
public class SysDeptEntity extends TenantBaseEntity {

    /**
     * 部门名称
     */
    private String name;

    /**
     * 上级部门ID
     */
    private Long pid;

    /**
     * 显示顺序
     */
    private Integer sort;

    /**
     * 负责人ID
     */
    private Long leaderId;

    /**
     * 联系电话
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 部门状态
     */
    private Integer status;

    public static final Long PID_ROOT = 0L;

}
