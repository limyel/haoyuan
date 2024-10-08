package com.limyel.haoyuan.mall.common.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Schema(title = "刷新 token DTO")
public class RefreshDTO {

    @NotBlank(message = "access token 不能为空")
    @Schema(title = "access token")
    private String accessToken;

    @NotBlank(message = "access token 不能为空")
    @Schema(title = "refresh token")
    private String refreshToken;

}
