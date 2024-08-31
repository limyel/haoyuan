package com.limyel.haoyuan.mall.member.dto.pointlog;

import lombok.Data;

@Data
public class PointChange {

    private Long changedPoint;

    private Integer type;

    private String reason;

    private Long userId;

}
