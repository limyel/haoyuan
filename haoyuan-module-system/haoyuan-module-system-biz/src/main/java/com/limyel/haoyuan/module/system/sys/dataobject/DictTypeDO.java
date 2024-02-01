package com.limyel.haoyuan.module.system.sys.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.framework.mybatis.pojo.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_dict_type")
public class DictTypeDO extends BaseDO {

    /**
     * 字典名称
     */
    private String name;

    /**
     * 字典类型
     */
    private String type;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

}
