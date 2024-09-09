package com.limyel.haoyuan.mallcloud.auth.dto;

import lombok.Data;

@Data
public class RefreshDTO {

    private String accessToken;

    private String refreshToken;

}
