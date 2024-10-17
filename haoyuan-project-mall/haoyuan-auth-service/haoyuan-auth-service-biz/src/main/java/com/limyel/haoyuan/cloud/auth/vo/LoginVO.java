package com.limyel.haoyuan.cloud.auth.vo;

import lombok.Data;

@Data
public class LoginVO {

    private String accessToken;

    private String refreshToken;

}
