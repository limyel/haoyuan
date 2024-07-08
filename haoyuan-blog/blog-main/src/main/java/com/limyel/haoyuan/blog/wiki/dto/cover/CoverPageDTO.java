package com.limyel.haoyuan.blog.wiki.dto.cover;

import com.limyel.haoyuan.common.web.pojo.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class CoverPageDTO extends PageParam {

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String title;

}
