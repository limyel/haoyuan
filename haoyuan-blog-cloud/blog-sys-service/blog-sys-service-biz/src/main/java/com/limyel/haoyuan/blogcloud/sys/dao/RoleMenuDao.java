package com.limyel.haoyuan.blogcloud.sys.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.limyel.haoyuan.blogcloud.sys.entity.RoleMenuEntity;
import com.limyel.haoyuan.common.mybatis.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMenuDao extends BaseDao<RoleMenuEntity> {

    default List<RoleMenuEntity> selectByRoleIds(List<Long> roleIds) {
        return selectList(new LambdaQueryWrapper<RoleMenuEntity>()
                .in(RoleMenuEntity::getRoleId, roleIds));
    }

}