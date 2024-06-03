package com.limyel.haoyuan.system.service;

import com.limyel.haoyuan.common.mybatis.pojo.PageData;
import com.limyel.haoyuan.system.entity.PostEntity;
import com.limyel.haoyuan.system.dto.post.PostDTO;
import com.limyel.haoyuan.system.dto.post.PostPageDTO;

import java.util.List;

public interface PostService {

    Long create(PostDTO dto);

    void update(PostDTO dto);

    void delete(Long id);

    PostEntity get(Long id);

    List<PostEntity> getList(PostPageDTO dto);

    PageData<PostEntity> getPage(PostPageDTO dto);

}