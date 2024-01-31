package com.limyel.haoyuan.module.system.service.dept;

import com.limyel.haoyuan.framework.mybatis.pojo.PageData;
import com.limyel.haoyuan.module.system.dataobject.dept.DeptDO;
import com.limyel.haoyuan.module.system.dto.dept.DeptDTO;
import com.limyel.haoyuan.module.system.dto.dept.DeptPageDTO;

import java.util.List;

public interface DeptService {

    Long create(DeptDTO dto);

    void update(DeptDTO dto);

    void delete(Long id);

    DeptDO get(Long id);

    PageData<DeptDO> getPage(DeptPageDTO dto);

    List<DeptDO> getList(DeptPageDTO dto);

}
