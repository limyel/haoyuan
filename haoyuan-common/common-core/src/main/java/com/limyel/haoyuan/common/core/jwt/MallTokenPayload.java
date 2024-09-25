package com.limyel.haoyuan.common.core.jwt;

import lombok.Data;

@Data
public class MallTokenPayload {

    private Long sysUserId;

    private Long memberUserId;

}
