package com.limyel.haoyuan.system.dao;

import com.limyel.haoyuan.common.mybatis.dao.BaseDao;
import com.limyel.haoyuan.common.mybatis.query.LambdaQueryWrapperPlus;
import com.limyel.haoyuan.system.domain.MenuDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper
public interface MenuDao extends BaseDao<MenuDO> {

    default MenuDO selectByPidAndName(Long pid, String name) {
        return selectOne(new LambdaQueryWrapperPlus<MenuDO>()
                .eqIfPresent(MenuDO::getPid, pid)
                .eqIfPresent(MenuDO::getName, name));
    }

    default List<MenuDO> selectByIds(Collection<Long> ids) {
        return selectList(new LambdaQueryWrapperPlus<MenuDO>()
                .inIfPresent(MenuDO::getId, ids));
    }

}