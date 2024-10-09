package com.limyel.haoyuan.cloud.member.dto.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserDTO {

    @NotBlank(message = "用户名不能为空")
    private String username;

    private String password;

    private String checkPassword;

}
