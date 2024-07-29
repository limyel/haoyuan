package com.limyel.haoyuan.blog.main.vo.tag;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TagPageVO {

    private String name;

    private String slug;

    private String remark;

    private LocalDateTime createTime;

}
