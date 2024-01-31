package com.limyel.haoyuan.module.system.dataobject.dict;

import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.framework.mybatis.pojo.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_dict_data")
public class SysDictDataDO extends BaseDO {

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
    private String dictType;

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
