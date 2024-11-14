package com.limyel.cloud.sys.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.common.mybatis.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 登录日志
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("log_login")
public class LogLoginEntity extends BaseEntity {

    /**
     * 用户名
     */
    private String username;

    /**
     * 主机地址
     */
    @TableField("ip")
    private String IP;

    /**
     * 状态，0-成功 1-失败
     */
    private Integer status;

}
