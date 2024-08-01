package com.limyel.haoyuan.blog.sys.dto.user;

import com.limyel.haoyuan.common.core.validator.group.Create;
import com.limyel.haoyuan.common.core.validator.group.Update;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
public class UserDTO {

    @NotNull(groups = Update.class, message = "ID 不能为空")
    @Null(groups = Create.class, message = "不能指定 ID")
    @ApiModelProperty(value = "ID")
    private Long id;

    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(groups = Create.class, message = "密码不能为空")
    private String password;

}
