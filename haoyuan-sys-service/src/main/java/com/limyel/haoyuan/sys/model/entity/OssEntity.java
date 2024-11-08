package com.limyel.haoyuan.sys.model.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import com.limyel.haoyuan.common.mybatis.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_oss")
public class OssEntity extends BaseEntity {

    /**
     * 文件名
     */
    private String filename;

    /**
     * 原名
     */
    private String originalName;

    /**
     * 后缀名
     */
    private String suffix;

    /**
     * URL 地址
     */
    private String url;

    /**
     * 服务商类型
     */
    private Integer serviceType;

}
