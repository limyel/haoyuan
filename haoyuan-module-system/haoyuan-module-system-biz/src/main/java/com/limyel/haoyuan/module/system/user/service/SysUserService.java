package com.limyel.haoyuan.module.system.user.service;

import com.limyel.haoyuan.framework.mybatis.pojo.PageData;
import com.limyel.haoyuan.module.system.user.dto.SysUserDTO;
import com.limyel.haoyuan.module.system.user.dto.SysUserFilterDTO;
import com.limyel.haoyuan.module.system.user.dd.SysUserEntity;

import java.util.List;

public interface SysUserService {

    Long create(SysUserDTO dto);

    void update(SysUserDTO dto);

    void delete(Long id);

    SysUserEntity get(Long id);

    List<SysUserEntity> getList(SysUserFilterDTO dto);

    PageData<SysUserEntity> getPage(SysUserFilterDTO dto);

}
