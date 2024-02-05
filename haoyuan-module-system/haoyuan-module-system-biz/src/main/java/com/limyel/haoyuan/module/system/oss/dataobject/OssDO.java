package com.limyel.haoyuan.module.system.oss.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.framework.mybatis.pojo.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_oss")
public class OssDO extends BaseDO {

    /**
     * 文件地址
     */
    private String url;

}
