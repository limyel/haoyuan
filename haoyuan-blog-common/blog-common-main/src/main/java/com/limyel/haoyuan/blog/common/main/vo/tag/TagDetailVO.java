package com.limyel.haoyuan.blog.common.main.vo.tag;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TagDetailVO extends TagPostVO {

    private long postNum;

}
