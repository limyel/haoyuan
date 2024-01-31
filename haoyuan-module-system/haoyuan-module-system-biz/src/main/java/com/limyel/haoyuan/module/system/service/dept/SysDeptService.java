package com.limyel.haoyuan.module.system.service.dept;

import com.limyel.haoyuan.framework.mybatis.pojo.PageData;
import com.limyel.haoyuan.module.system.dataobject.dept.SysDeptDO;
import com.limyel.haoyuan.module.system.dto.dept.SysDeptDTO;
import com.limyel.haoyuan.module.system.dto.dept.SysDeptFilterDTO;

import java.util.List;

public interface SysDeptService {

    Long create(SysDeptDTO dto);

    void update(SysDeptDTO dto);

    void delete(Long id);

    SysDeptDO get(Long id);

    PageData<SysDeptDO> getPage(SysDeptFilterDTO dto);

    List<SysDeptDO> getList(SysDeptFilterDTO dto);

}
