package com.limyel.haoyuan.module.system.dept.service;

import com.limyel.haoyuan.framework.mybatis.pojo.PageData;
import com.limyel.haoyuan.module.system.dept.dto.SysDeptDTO;
import com.limyel.haoyuan.module.system.dept.dto.req.SysDeptFilterReq;
import com.limyel.haoyuan.module.system.dept.entity.SysDeptEntity;

import java.util.Collection;
import java.util.List;

public interface SysDeptService {

    Long create(SysDeptDTO dto);

    void update(SysDeptDTO dto);

    void delete(Long id);

    SysDeptEntity get(Long id);

    PageData<SysDeptEntity> getPage(SysDeptFilterReq req);

    List<SysDeptEntity> getList(SysDeptFilterReq req);

}
