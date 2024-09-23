package com.limyel.haoyuan.blog.common.main.dto.post;

import com.limyel.haoyuan.common.core.pojo.PageParam;
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
