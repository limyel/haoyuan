package com.limyel.haoyuan.module.system.sys.service;

import com.limyel.haoyuan.framework.mybatis.pojo.PageData;
import com.limyel.haoyuan.module.system.sys.entity.SysUserDO;
import com.limyel.haoyuan.module.system.sys.dto.user.SysUserDTO;
import com.limyel.haoyuan.module.system.sys.dto.user.SysUserPageDTO;

import java.util.List;

public interface SysUserService {

    Long create(SysUserDTO dto);

    void update(SysUserDTO dto);

    void delete(Long id);

    SysUserDO get(Long id);

    List<SysUserDO> getList(SysUserPageDTO dto);

    PageData<SysUserDO> getPage(SysUserPageDTO dto);

    SysUserDO getByUsername(String username);

}
