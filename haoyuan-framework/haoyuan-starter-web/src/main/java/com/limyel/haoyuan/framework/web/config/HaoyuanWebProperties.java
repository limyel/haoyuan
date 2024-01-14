package com.limyel.haoyuan.framework.web.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ConfigurationProperties(prefix = "haoyuan.web")
public class HaoyuanWebProperties {

    private Api appApi = new Api("/app-api", "**.controller.app.**");
    private Api sysApi = new Api("/sys-api", "**.controller.sys.**");

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Valid
    public static class Api {

        @NotBlank(message = "API 前缀不能为空")
        private String prefix;

        @NotBlank(message = "Controller 所在包不能为空")
        private String controller;

    }

}
