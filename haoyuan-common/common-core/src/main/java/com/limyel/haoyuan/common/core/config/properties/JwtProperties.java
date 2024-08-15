package com.limyel.haoyuan.common.core.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "haoyuan.jwt")
public class JwtProperties {

    /**
     * 签发人
     */
    private String issuer;

    /**
     * 密钥
     */
    // todo 默认值
    private String secret = "mhf6b3wbVxmmUWFgeqGMTBoSR9s9rUl4QeyYwZTTBRvybd+ZDmqB8/mYy0yOO201acd7Sia4b7JNSP174DtUoQ==";

    /**
     * 有效时长（小时）
     */
    private Integer expireHours;

}
