package com.limyel.haoyuan.system.service;

import com.limyel.haoyuan.common.mybatis.pojo.PageData;
import com.limyel.haoyuan.system.domain.DeptDO;
import com.limyel.haoyuan.system.dto.dept.DeptDTO;
import com.limyel.haoyuan.system.dto.dept.DeptPageDTO;

import java.util.List;

public interface DeptService {

    Long create(DeptDTO dto);

    void update(DeptDTO dto);

    void delete(Long id);

    DeptDO get(Long id);

    PageData<DeptDO> getPage(DeptPageDTO dto);

    List<DeptDO> getList(DeptPageDTO dto);

}