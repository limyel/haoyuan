package com.limyel.haoyuan.module.system.sys.dao;

import com.limyel.haoyuan.framework.mybatis.dao.BaseDao;
import com.limyel.haoyuan.framework.mybatis.query.LambdaQueryWrapperPlus;
import com.limyel.haoyuan.module.system.sys.dataobject.MenuDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MenuDao extends BaseDao<MenuDO> {

    default MenuDO selectByPidAndName(Long pid, String name) {
        return selectOne(new LambdaQueryWrapperPlus<MenuDO>()
                .eqIfPresent(MenuDO::getPid, pid)
                .eqIfPresent(MenuDO::getName, name));
    }

}
