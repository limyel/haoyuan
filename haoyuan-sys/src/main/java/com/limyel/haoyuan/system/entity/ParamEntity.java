package com.limyel.haoyuan.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.common.mybatis.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_param")
public class ParamEntity extends BaseEntity {

    /**
     * 编码
     */
    private String code;

    /**
     * 参数值
     */
    private String value;

    /**
     * 类型，1：内置参数，2：自定义参数
     */
    private Integer type;

    /**
     * 备注
     */
    private String remark;

}