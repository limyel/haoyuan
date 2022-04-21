package com.limyel.haoyuan.config;

import com.limyel.haoyuan.entity.User;
import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * 整合 swagger2
 */
//@Profile({"dev", "test"})
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        List<Parameter> parameters = new ArrayList<>();
        parameters.add(new ParameterBuilder().name("Authorization").description("jwt token").modelRef(new ModelRef("string")).parameterType("header").build());

        Docket docket =  new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .paths(PathSelectors.any())                                             // 所有包
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))           // 查找 Api 注解的类
                .build();
        // 全局参数
        docket.globalOperationParameters(parameters);
        // 忽略方法中的参数
        docket.ignoredParameterTypes(User.class);

        ApiKey apiKey = new ApiKey("Authorization", "jwt token", "header");
        List<ApiKey> apiKeys = new ArrayList<>();
        apiKeys.add(apiKey);
        docket.securitySchemes(apiKeys);

        AuthorizationScope scope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] scopes = {scope};
        SecurityReference reference = new SecurityReference("token", scopes);
        List refList = new ArrayList();
        refList.add(reference);
        SecurityContext securityContext = SecurityContext.builder().securityReferences(refList).build();
        List cxtList = new ArrayList();
        cxtList.add(securityContext);
        docket.securityContexts(cxtList);

        return docket;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Haoyuan")
                .description("haoyuan-mp-backend 的 RESTFul 接口文档说明")
                .version("0.0.1").build();
    }


}
