package com.limyel.haoyuan.module.system.dept.service;

import com.limyel.haoyuan.framework.mybatis.pojo.PageData;
import com.limyel.haoyuan.module.system.dept.dataobject.SysPostDO;
import com.limyel.haoyuan.module.system.dept.dto.SysPostDTO;
import com.limyel.haoyuan.module.system.dept.dto.SysPostFilterDTO;

import java.util.List;

public interface SysPostService {

    Long create(SysPostDTO dto);

    void update(SysPostDTO dto);

    void delete(Long id);

    SysPostDO get(Long id);

    List<SysPostDO> getList(SysPostFilterDTO dto);

    PageData<SysPostDO> getPage(SysPostFilterDTO dto);

}
