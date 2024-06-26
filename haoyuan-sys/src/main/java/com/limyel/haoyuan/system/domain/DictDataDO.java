package com.limyel.haoyuan.system.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.common.mybatis.pojo.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_dict_data")
public class DictDataDO extends BaseDO {

    /**
     * 字典排序
     */
    private Integer sort;

    /**
     * 字典标签
     */
    private String label;

    /**
     * 字典键值
     */
    private String value;

    /**
     * 字典类型
     */
    private String type;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 颜色类型
     */
    private String colorType;

    /**
     * css 样式
     */
    private String cssClass;

    /**
     * 备注
     */
    private String remark;

}