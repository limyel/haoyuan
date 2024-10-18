package com.limyel.haoyuan.cloud.member.vo.paylog;

import lombok.Data;

@Data
public class PayLogListVO {

    private Integer type;

    private Long changedPoint;

    private Long changedBalance;

    private String reason;

}
