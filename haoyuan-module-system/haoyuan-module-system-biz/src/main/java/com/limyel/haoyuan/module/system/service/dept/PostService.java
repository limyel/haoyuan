package com.limyel.haoyuan.module.system.service.dept;

import com.limyel.haoyuan.framework.mybatis.pojo.PageData;
import com.limyel.haoyuan.module.system.dataobject.dept.PostDO;
import com.limyel.haoyuan.module.system.dto.dept.PostDTO;
import com.limyel.haoyuan.module.system.dto.dept.PostPageDTO;

import java.util.List;

public interface PostService {

    Long create(PostDTO dto);

    void update(PostDTO dto);

    void delete(Long id);

    PostDO get(Long id);

    List<PostDO> getList(PostPageDTO dto);

    PageData<PostDO> getPage(PostPageDTO dto);

}
