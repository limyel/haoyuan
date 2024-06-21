package com.limyel.haoyuan.blog.main.vo.post;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PostDetailVO extends PostListVO {

    private String content;

}
