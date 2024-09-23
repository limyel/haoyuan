package com.limyel.haoyuan.blog.common.main.dto.tag;

import com.limyel.haoyuan.common.core.pojo.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class TagPageDTO extends PageParam {

    private String name;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

}
