package com.limyel.haoyuan.blog.main.dto.setting;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serial;
import java.io.Serializable;

@Data
public class SettingDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "博客名称不能为空")
    private String name;

    @NotBlank(message = "关于不能为空")
    private String about;

}
