package com.limyel.haoyuan.common.core.jwt;

import lombok.Data;

import java.util.Date;

@Data
public class TokenPayload {

    private String id;

    private String payload;

    private Date expiration;

}
