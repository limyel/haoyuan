package com.limyel.haoyuan.mall.sys.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.limyel.haoyuan.mall.common.sys.entity.RoleMenuEntity;
import com.limyel.haoyuan.mall.sys.dao.RoleMenuDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleMenuService {

    private final RoleMenuDao roleMenuDao;

    List<Long> getMenuIdList(List<Long> roleIds) {
        List<RoleMenuEntity> list = roleMenuDao.selectList(new LambdaQueryWrapper<RoleMenuEntity>()
                .in(RoleMenuEntity::getRoleId, roleIds));
        if (list.isEmpty()) {
            return Collections.emptyList();
        }
        return list.stream()
                .map(RoleMenuEntity::getMenuId)
                .toList();
    }

}
