package com.limyel.haoyuan.admin.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {
    @Bean
    public OpenAPI haoyuanAdminOpenApi() {
        return new OpenAPI()
                .info(new Info().title("HaoYuan API")
                        .description("浩元管理后台接口")
                        .version("v1.0.0"));
    }

    @Bean
    public GroupedOpenApi adminApi() {
        return GroupedOpenApi.builder()
                .group("admin")
                .packagesToScan("com.limyel.haoyuan.admin.controller")
                .build();
    }

}
