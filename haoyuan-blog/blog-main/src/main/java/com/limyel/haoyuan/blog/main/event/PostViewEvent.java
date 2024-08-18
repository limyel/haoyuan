package com.limyel.haoyuan.blog.main.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class PostViewEvent extends ApplicationEvent {

    private Long postId;

    public PostViewEvent(Object source, Long postId) {
        super(source);
        this.postId = postId;
    }

}
