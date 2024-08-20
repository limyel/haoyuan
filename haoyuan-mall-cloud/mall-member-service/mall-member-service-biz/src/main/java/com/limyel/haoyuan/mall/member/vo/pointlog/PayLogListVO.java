package com.limyel.haoyuan.mall.member.vo.pointlog;

import lombok.Data;

@Data
public class PayLogListVO {

    private Integer type;

    private Long changedPoint;

    private Long changedBalance;

    private String reason;

}
