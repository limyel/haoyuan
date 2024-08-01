package com.limyel.haoyuan.member.dto.point;

import com.limyel.haoyuan.common.core.pojo.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PointPageDTO extends PageParam {

    private String username;

}
