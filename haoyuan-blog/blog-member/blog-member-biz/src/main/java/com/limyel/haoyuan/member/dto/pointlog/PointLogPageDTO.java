package com.limyel.haoyuan.member.dto.pointlog;

import com.limyel.haoyuan.common.core.pojo.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PointLogPageDTO extends PageParam {

    private String username;

}
