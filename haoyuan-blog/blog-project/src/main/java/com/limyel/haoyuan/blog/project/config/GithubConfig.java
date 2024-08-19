package com.limyel.haoyuan.blog.project.config;

import com.limyel.haoyuan.blog.project.config.properties.GithubProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(GithubProperties.class)
public class GithubConfig {
}
