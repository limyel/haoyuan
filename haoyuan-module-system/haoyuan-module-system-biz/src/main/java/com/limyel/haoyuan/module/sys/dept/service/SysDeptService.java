package com.limyel.haoyuan.module.sys.dept.service;

import com.limyel.haoyuan.framework.mybatis.pojo.PageData;
import com.limyel.haoyuan.module.sys.dept.dto.SysDeptDTO;
import com.limyel.haoyuan.module.sys.dept.dto.SysDeptFilterDTO;
import com.limyel.haoyuan.module.sys.dept.entity.SysDeptEntity;

import java.util.List;

public interface SysDeptService {

    Long create(SysDeptDTO dto);

    void update(SysDeptDTO dto);

    void delete(Long id);

    SysDeptEntity get(Long id);

    PageData<SysDeptEntity> getPage(SysDeptFilterDTO dto);

    List<SysDeptEntity> getList(SysDeptFilterDTO dto);

}
