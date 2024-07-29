package com.limyel.haoyuan.blog.content.dto.tag;

import com.limyel.haoyuan.common.web.pojo.PageParam;
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
