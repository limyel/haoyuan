package com.limyel.haoyuan.mall.auth.dto;

import lombok.Data;

@Data
public class LoginDTO {

    private String clientId;

    private String grantType;

    private String authInfo;

    private String code;

    private String uuid;

}
