package com.limyel.haoyuan.common.jwt.config;

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
    private String secret;

    private String header;

}
