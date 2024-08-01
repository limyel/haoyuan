package com.limyel.haoyuan.member.dto.point;

import lombok.Data;

@Data
public class PointUpdateDTO {

    private Long userId;

    private Integer point;

    private String reason;

    private Integer type;

}
