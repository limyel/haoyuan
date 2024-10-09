package com.limyel.haoyuan.mallcloud.member.vo.paylog;

import lombok.Data;

@Data
public class PayLogPageVO {

    private Long userId;

    private String username;

    private Integer type;

    private Long changedPoint;

    private Long changedBalance;

    private String reason;

}
