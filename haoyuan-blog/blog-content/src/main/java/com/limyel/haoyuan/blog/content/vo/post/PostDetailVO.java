package com.limyel.haoyuan.blog.content.vo.post;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class PostDetailVO extends PostListVO {

    private String content;

    private LocalDateTime updateTime;

}
