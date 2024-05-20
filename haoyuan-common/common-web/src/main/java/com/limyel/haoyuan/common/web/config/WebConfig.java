package com.limyel.haoyuan.common.web.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@AutoConfiguration
@EnableConfigurationProperties(WebProperties.class)
public class WebConfig implements WebMvcConfigurer {

    @Resource
    private WebProperties webProperties;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                // 允许跨域的路径
                .addMapping("/**")
                // 允许跨域请求的域名
                .allowedOriginPatterns("*")
                // 允许 cookie
                .allowCredentials(true)
                .allowedMethods(HttpMethod.GET.name(), HttpMethod.POST.name())
                // 允许的请求头属性
                .allowedHeaders("*")
                // 跨域允许时间
                .maxAge(3600);
    }

}