package com.limyel.haoyuan.blog.content.dto.post;

import com.limyel.haoyuan.common.web.pojo.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class PostPageDTO extends PageParam {

    private String title;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

}
