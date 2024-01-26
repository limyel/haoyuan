package com.limyel.haoyuan.module.system.dept.service;

import com.limyel.haoyuan.framework.mybatis.pojo.PageData;
import com.limyel.haoyuan.module.system.dept.dto.SysPostDTO;
import com.limyel.haoyuan.module.system.dept.dto.SysPostFilterDTO;
import com.limyel.haoyuan.module.system.dept.entity.SysPostEntity;

import java.util.List;

public interface SysPostService {

    Long create(SysPostDTO dto);

    void update(SysPostDTO dto);

    void delete(Long id);

    SysPostEntity get(Long id);

    List<SysPostEntity> getList(SysPostFilterDTO dto);

    PageData<SysPostEntity> getPage(SysPostFilterDTO dto);

}
