package com.limyel.haoyuan.blog.admin.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UpdatePasswordDTO {

    @NotNull(message = "用户 ID 不能为空")
    private Long id;

    @NotBlank(message = "密码不能为空")
    private String password;

}
