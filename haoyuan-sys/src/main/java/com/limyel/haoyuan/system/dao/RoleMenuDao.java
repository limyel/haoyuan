package com.limyel.haoyuan.system.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.limyel.haoyuan.common.mybatis.dao.BaseDao;
import com.limyel.haoyuan.system.domain.RoleMenuDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMenuDao extends BaseDao<RoleMenuDO> {

    default List<RoleMenuDO> selectByRoleIds(List<Long> roleIds) {
        return selectList(new LambdaQueryWrapper<RoleMenuDO>()
                .in(RoleMenuDO::getRoleId, roleIds));
    }

}