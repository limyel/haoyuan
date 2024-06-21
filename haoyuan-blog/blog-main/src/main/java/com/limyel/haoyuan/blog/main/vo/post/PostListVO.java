package com.limyel.haoyuan.blog.main.vo.post;

import com.limyel.haoyuan.blog.main.vo.tag.TagPostVO;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PostListVO {

    private String title;

    private String slug;

    private String summary;

    private LocalDateTime createTime;

    private int viewNum;

    private List<TagPostVO> tags;

}
