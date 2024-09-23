package com.limyel.haoyuan.blog.common.main.vo.post;

import com.limyel.haoyuan.blog.common.main.vo.tag.TagPostVO;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PostArchiveVO {

    private int year;

    private List<Item> posts;

    @Data
    public static class Item {

        private String title;

        private String slug;

        private LocalDateTime createTime;

        private List<TagPostVO> tags;

    }

}
