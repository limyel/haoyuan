package com.limyel.haoyuan.common.knife4j.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.PrivateKey;

@Data
@ConfigurationProperties(prefix = "haoyuan.knife4j")
public class Knife4jProperties {

    /**
     * 标题
     */
    private String title;

    /**
     * 描述
     */
    private String description;

    /**
     * API 服务条款
     */
    private String termsOfServiceUrl;

    /**
     * 用户名
     */
    private String username;

    /**
     * 主页地址
     */
    private String homeUrl;

    /**
     * 邮箱地址
     */
    private String email;

    /**
     * 版本号
     */
    private String version;


    /**
     * 分组名称
     */
    private String groupName;

    /**
     * 扫描包路径
     */
    private String basePackage;
}
