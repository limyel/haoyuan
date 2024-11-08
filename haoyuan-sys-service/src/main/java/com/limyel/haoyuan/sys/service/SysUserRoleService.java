package com.limyel.haoyuan.sys.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.limyel.haoyuan.cloud.sys.dao.SysUserRoleDao;
import com.limyel.haoyuan.cloud.sys.entity.SysUserRoleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SysUserRoleService {

    private final SysUserRoleDao sysUserRoleDao;

    public List<Long> getRoleIdList(Long sysUserId) {
        List<SysUserRoleEntity> list = sysUserRoleDao.selectList(new LambdaQueryWrapper<SysUserRoleEntity>()
                .eq(SysUserRoleEntity::getSysUserId, sysUserId));
        if (list.isEmpty()) {
            return Collections.emptyList();
        }
        return list.stream()
                .map(SysUserRoleEntity::getRoleId)
                .toList();
    }

    public Long countByRoleId(Long roleId) {
        return sysUserRoleDao.selectCount(new LambdaQueryWrapper<SysUserRoleEntity>()
                .eq(SysUserRoleEntity::getRoleId, roleId));
    }

}
