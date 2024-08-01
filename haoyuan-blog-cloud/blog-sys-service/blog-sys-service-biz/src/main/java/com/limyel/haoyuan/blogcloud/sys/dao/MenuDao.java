package com.limyel.haoyuan.blogcloud.sys.dao;

import com.limyel.haoyuan.blogcloud.sys.entity.MenuEntity;
import com.limyel.haoyuan.common.mybatis.dao.BaseDao;
import com.limyel.haoyuan.common.mybatis.query.LambdaQueryWrapperPlus;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper
public interface MenuDao extends BaseDao<MenuEntity> {

    default MenuEntity selectByPidAndName(Long pid, String name) {
        return selectOne(new LambdaQueryWrapperPlus<MenuEntity>()
                .eqIfPresent(MenuEntity::getPid, pid)
                .eqIfPresent(MenuEntity::getName, name));
    }

    default List<MenuEntity> selectByIds(Collection<Long> ids) {
        return selectList(new LambdaQueryWrapperPlus<MenuEntity>()
                .inIfPresent(MenuEntity::getId, ids));
    }

}