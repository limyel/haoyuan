package com.limyel.haoyuan.blog.sys.service;

import com.limyel.haoyuan.blog.common.sys.entity.SysUserRoleEntity;
import com.limyel.haoyuan.blog.sys.dao.SysUserRoleDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserRoleService {

    private final SysUserRoleDao sysUserRoleDao;

    public List<SysUserRoleEntity> getByUserId(Long userId) {
        return sysUserRoleDao.selectList(SysUserRoleEntity::getSysUserId, userId);
    }

}
