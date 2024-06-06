package com.limyel.haoyuan.system.service;

import com.limyel.haoyuan.common.mybatis.pojo.PageData;
import com.limyel.haoyuan.system.domain.PostDO;
import com.limyel.haoyuan.system.dto.post.PostDTO;
import com.limyel.haoyuan.system.dto.post.PostPageDTO;

import java.util.List;

public interface PostService {

    Long create(PostDTO dto);

    void update(PostDTO dto);

    void delete(Long id);

    PostDO get(Long id);

    List<PostDO> getList(PostPageDTO dto);

    PageData<PostDO> getPage(PostPageDTO dto);

}