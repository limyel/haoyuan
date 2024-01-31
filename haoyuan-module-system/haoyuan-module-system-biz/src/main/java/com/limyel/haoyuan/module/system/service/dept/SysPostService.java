package com.limyel.haoyuan.module.system.service.dept;

import com.limyel.haoyuan.framework.mybatis.pojo.PageData;
import com.limyel.haoyuan.module.system.dataobject.dept.SysPostDO;
import com.limyel.haoyuan.module.system.dto.dept.SysPostDTO;
import com.limyel.haoyuan.module.system.dto.dept.SysPostFilterDTO;

import java.util.List;

public interface SysPostService {

    Long create(SysPostDTO dto);

    void update(SysPostDTO dto);

    void delete(Long id);

    SysPostDO get(Long id);

    List<SysPostDO> getList(SysPostFilterDTO dto);

    PageData<SysPostDO> getPage(SysPostFilterDTO dto);

}
