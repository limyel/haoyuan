package com.limyel.haoyuan.cloud.member.dto.paylog;

import com.limyel.haoyuan.common.core.pojo.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PayLogPageDTO extends PageParam {

    private Long userId;

}
