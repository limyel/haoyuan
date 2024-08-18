package com.limyel.haoyuan.blog.project.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "haoyuan.github")
public class GithubProperties {

    private String token;

    private String username;

}
