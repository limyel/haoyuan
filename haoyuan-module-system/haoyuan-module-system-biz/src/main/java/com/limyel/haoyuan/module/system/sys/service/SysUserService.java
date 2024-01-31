package com.limyel.haoyuan.module.system.sys.service;

import com.limyel.haoyuan.framework.mybatis.pojo.PageData;
import com.limyel.haoyuan.module.system.sys.dataobject.SysUserDO;
import com.limyel.haoyuan.module.system.sys.dto.user.SysUserDTO;
import com.limyel.haoyuan.module.system.sys.dto.user.SysUserFilterDTO;

import java.util.List;

public interface SysUserService {

    Long create(SysUserDTO dto);

    void update(SysUserDTO dto);

    void delete(Long id);

    SysUserDO get(Long id);

    List<SysUserDO> getList(SysUserFilterDTO dto);

    PageData<SysUserDO> getPage(SysUserFilterDTO dto);

}
