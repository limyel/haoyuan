package com.limyel.haoyuan.mall.common.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Schema(title = "校验 token DTO")
public class CheckTokenDTO {

    @NotBlank(message = "token 不能为空")
    @Schema(title = "token")
    private String token;

}
