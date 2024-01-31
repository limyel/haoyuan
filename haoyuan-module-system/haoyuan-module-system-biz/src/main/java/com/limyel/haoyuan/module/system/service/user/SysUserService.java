package com.limyel.haoyuan.module.system.service.user;

import com.limyel.haoyuan.framework.mybatis.pojo.PageData;
import com.limyel.haoyuan.module.system.dataobject.user.SysUserDO;
import com.limyel.haoyuan.module.system.dto.user.SysUserDTO;
import com.limyel.haoyuan.module.system.dto.user.SysUserFilterDTO;

import java.util.List;

public interface SysUserService {

    Long create(SysUserDTO dto);

    void update(SysUserDTO dto);

    void delete(Long id);

    SysUserDO get(Long id);

    List<SysUserDO> getList(SysUserFilterDTO dto);

    PageData<SysUserDO> getPage(SysUserFilterDTO dto);

}
