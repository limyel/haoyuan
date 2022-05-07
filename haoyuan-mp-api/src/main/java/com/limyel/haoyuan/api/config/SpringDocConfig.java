package com.limyel.haoyuan.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {
    @Bean
    public OpenAPI haoyuanPortalOpenApi() {
        return new OpenAPI()
                .info(new Info().title("HaoYuan API")
                        .description("浩元小程序接口")
                        .version("v1.0.0"));
    }

    @Bean
    public GroupedOpenApi portalApi() {
        return GroupedOpenApi.builder()
                .group("portal")
                .packagesToScan("com.limyel.haoyuan.api.controller")
                .build();
    }

}
