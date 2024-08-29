package com.limyel.haoyuan.mall.member.dto.level;

import com.limyel.haoyuan.common.core.pojo.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class LevelPageDTO extends PageParam {

    private String name;

}
