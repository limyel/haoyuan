package com.limyel.cloud.sys.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.common.mybatis.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("dept")
public class DeptEntity extends BaseEntity {

    /**
     * 上级 ID
     */
    private Long pid;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 显示顺序
     */
    private Integer sort;

    /**
     * 负责人
     */
    private Long leader;

    /**
     * 状态，0-停用 1-正常
     */
    private Integer status;

}
