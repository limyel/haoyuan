package com.limyel.haoyuan.mall.common.member.vo.level;

import lombok.Data;

@Data
public class LevelPageVO {

    private Long id;

    private String name;

    private Long minPoint;

    private Long maxPoint;

    private Integer sort;

}
