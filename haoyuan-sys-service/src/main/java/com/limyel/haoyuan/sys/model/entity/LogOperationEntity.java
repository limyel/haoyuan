package com.limyel.haoyuan.sys.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.common.mybatis.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 操作日志
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_log_operation")
public class LogOperationEntity extends BaseEntity {

    /**
     * 模块标题
     */
    private String title;

    /**
     * 业务类型，0-其他，1-新增，2-修改，3-删除
     */
    private Integer bizType;

    /**
     * 方法名称
     */
    private String method;

    /**
     * 请求方法
     */
    private String requestMethod;

    /**
     * 请求 URL
     */
    private String url;

    /**
     * 主机地址
     */
    @TableField("ip")
    private String IP;

    /**
     * 请求参数
     */
    private String param;

    /**
     * 响应结果
     */
    private String jsonResult;

    /**
     * 状态，0-正常，1-异常
     */
    private Integer status;

}
