package com.limyel.haoyuan.module.system.sys.service;

import com.limyel.haoyuan.framework.mybatis.pojo.PageData;
import com.limyel.haoyuan.module.system.sys.entity.PostDO;
import com.limyel.haoyuan.module.system.sys.dto.post.PostDTO;
import com.limyel.haoyuan.module.system.sys.dto.post.PostPageDTO;

import java.util.List;

public interface PostService {

    Long create(PostDTO dto);

    void update(PostDTO dto);

    void delete(Long id);

    PostDO get(Long id);

    List<PostDO> getList(PostPageDTO dto);

    PageData<PostDO> getPage(PostPageDTO dto);

}
