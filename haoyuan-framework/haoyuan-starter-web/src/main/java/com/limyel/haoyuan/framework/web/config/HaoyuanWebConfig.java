package com.limyel.haoyuan.framework.web.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@AutoConfiguration
@EnableConfigurationProperties(HaoyuanWebProperties.class)
public class HaoyuanWebConfig implements WebMvcConfigurer {

    @Resource
    private HaoyuanWebProperties haoyuanWebProperties;

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurePathMatch(configurer, haoyuanWebProperties.getSysApi());
        configurePathMatch(configurer, haoyuanWebProperties.getAppApi());
    }

    private void configurePathMatch(PathMatchConfigurer configurer, HaoyuanWebProperties.Api api) {
        AntPathMatcher antPathMatcher = new AntPathMatcher(".");
        configurer.addPathPrefix(api.getPrefix(), type -> type.isAnnotationPresent(RestController.class)
                && antPathMatcher.match(api.getController(), type.getPackage().getName()));
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public HaoyuanSwaggerConfig haoyuanSwaggerConfig() {
        return new HaoyuanSwaggerConfig();
    }
}
