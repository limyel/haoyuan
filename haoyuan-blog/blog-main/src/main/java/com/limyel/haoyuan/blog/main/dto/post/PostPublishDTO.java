package com.limyel.haoyuan.blog.main.dto.post;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostPublishDTO {

    private Long id;

    private LocalDateTime publishTime;

}
