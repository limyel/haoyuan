package com.limyel.haoyuan.member.vo.pointlog;

import lombok.Data;

@Data
public class PointLogPageVO {

    private Long id;

    private String username;

    private Integer type;

    private Integer changedPoint;

    private String reason;

}
