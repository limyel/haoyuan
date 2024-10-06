package com.limyel.haoyuan.mall.common.auth.vo;

import lombok.Data;

@Data
public class LoginVO {

    private String accessToken;

    private String refreshToken;

}
