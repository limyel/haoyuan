package com.limyel.haoyuan.blog.main.vo.post;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostPageVO {

    private Long id;

    private String title;

    private Boolean top;

    private Integer status;

    private LocalDateTime createTime;

}
