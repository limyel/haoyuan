package com.limyel.haoyuan.cloud.mall.trade.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info().title("mall-trade 接口文档"));
    }

    @Bean
    public GroupedOpenApi appGroup() {
        return GroupedOpenApi.builder()
                .group("客户端接口")
                .packagesToScan("com.limyel.haoyuan.cloud.mall.trade.controller.app")
                .build();
    }

    @Bean
    public GroupedOpenApi adminGroup() {
        return GroupedOpenApi.builder()
                .group("管理端接口")
                .packagesToScan("com.limyel.haoyuan.cloud.mall.trade.controller.admin")
                .build();
    }

}
