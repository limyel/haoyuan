package com.limyel.haoyuan.cloud.member.dto.paylog;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PointDTO {

    private Long point;

    private String reason;

    private String username;

    private LocalDateTime createTime;

}
