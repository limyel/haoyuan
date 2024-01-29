package com.limyel.haoyuan.framework.web.config;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Data
@Component
@EnableOpenApi
public class HaoyuanSwaggerConfig {

    @Bean
    public Docket webApiDoc() {
        return new Docket(DocumentationType.OAS_30)
                .groupName("浩元接口文档")
                .pathMapping("/")
                .enable(true)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.limyel.haoyuan"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("浩元")
                .description("浩元接口文档")
                .contact(new Contact("limyel", "https://limyel.com", "limyel@outlook.com"))
                .version("v1.0.0")
                .build();
    }

}
