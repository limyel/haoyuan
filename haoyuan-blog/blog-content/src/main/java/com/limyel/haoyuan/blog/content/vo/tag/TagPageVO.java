package com.limyel.haoyuan.blog.content.vo.tag;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TagPageVO {

    private String name;

    private String slug;

    private String remark;

    private LocalDateTime createTime;

}
