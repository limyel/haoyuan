package com.limyel.haoyuan.common.knife4j.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import javax.annotation.Resource;

@AutoConfiguration
@EnableSwagger2WebMvc
@EnableConfigurationProperties(Knife4jProperties.class)
@Profile("dev")
public class Knife4jConfig {

    @Resource
    private Knife4jProperties properties;

    @Bean("webApi")
    public Docket createApiDoc() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInfo())
                // 分组名称
                .groupName(properties.getGroupName())
                .select()
                // 指定 Controller 扫描包路径
                .apis(RequestHandlerSelectors.basePackage(properties.getBasePackage()))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 构建 Swagger 信息
     * @return
     */
    private ApiInfo buildApiInfo() {
        return new ApiInfoBuilder()
                .title(properties.getTitle())
                .description(properties.getDescription())
                .termsOfServiceUrl(properties.getTermsOfServiceUrl())
                .contact(new Contact(properties.getUsername(), properties.getHomeUrl(), properties.getEmail()))
                .version(properties.getVersion())
                .build();
    }

}
