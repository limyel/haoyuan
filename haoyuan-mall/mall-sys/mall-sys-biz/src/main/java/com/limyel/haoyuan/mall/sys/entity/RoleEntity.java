package com.limyel.haoyuan.mall.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.common.mybatis.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_role")
public class RoleEntity extends BaseEntity {

    /**
     * 角色名
     */
    private String name;

    /**
     * 角色编码
     */
    private String code;

    /**
     * 显示顺序
     */
    private Integer sort;

    /**
     * 状态，0-禁用，1-正常
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

}
