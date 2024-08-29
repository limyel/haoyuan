package com.limyel.haoyuan.mall.member.vo.auth;

import lombok.Data;

@Data
public class LoginVO {

    private String accessToken;

    private String refreshToken;

    private Long expireIn;

    private Long refreshExpireIn;

}
