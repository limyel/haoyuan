package com.limyel.haoyuan.blogcloud.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.common.mybatis.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName
public class RoleEntity extends BaseEntity {

    /**
     * 角色名
     */
    private String name;

    /**
     * 角色权限字符串
     */
    private String code;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

}