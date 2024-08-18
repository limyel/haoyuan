package com.limyel.haoyuan.blog.sync.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class PointAddEvent extends ApplicationEvent {

    private Long point;

    private String reason;

    private String username;

    public PointAddEvent(Object source, Long point, String reason, String username) {
        super(source);
        this.point = point;
        this.reason = reason;
        this.username = username;
    }

}
