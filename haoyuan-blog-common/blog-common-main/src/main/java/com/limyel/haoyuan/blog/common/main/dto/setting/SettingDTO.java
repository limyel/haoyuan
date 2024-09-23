package com.limyel.haoyuan.blog.common.main.dto.setting;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serial;
import java.io.Serializable;

@Data
public class SettingDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotBlank(message = "标签不能为空")
    private String label;

    @NotBlank(message = "值不能为空")
    private String value;

}
