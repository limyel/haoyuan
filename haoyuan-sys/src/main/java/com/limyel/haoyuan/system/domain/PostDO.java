package com.limyel.haoyuan.system.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.common.mybatis.pojo.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_post")
public class PostDO extends BaseDO {

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