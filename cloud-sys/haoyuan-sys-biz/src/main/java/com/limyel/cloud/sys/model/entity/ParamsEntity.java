package com.limyel.cloud.sys.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.common.mybatis.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("params")
public class ParamsEntity extends BaseEntity {

    /**
     * 参数标签
     */
    private String paramLabel;

    /**
     * 参数编码
     */
    private String paramCode;

    /**
     * 参数值
     */
    private String paramValue;

    /**
     * 参数类型，0-系统参数 1-非系统参数
     */
    private Integer paramType;

    /**
     * 备注
     */
    private String remark;

}
