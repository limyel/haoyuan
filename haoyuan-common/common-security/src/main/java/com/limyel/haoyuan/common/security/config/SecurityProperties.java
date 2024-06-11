package com.limyel.haoyuan.common.security.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "haoyuan.security")
public class SecurityProperties {

    private TokenProperties token;

    private String loginUrl;

    @Data
    public static class TokenProperties {

        private String header;

        private String prefix;

    }

}
