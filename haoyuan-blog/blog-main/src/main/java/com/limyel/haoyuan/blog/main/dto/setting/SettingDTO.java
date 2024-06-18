package com.limyel.haoyuan.blog.main.dto.setting;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class SettingDTO {

    @NotBlank(message = "博客名称不能为空")
    private String name;

    @NotBlank(message = "关于不能为空")
    private String about;

}
