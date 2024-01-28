package com.limyel.haoyuan.module.system.dept.dao;

import com.limyel.haoyuan.framework.mybatis.dao.BaseDao;
import com.limyel.haoyuan.framework.mybatis.query.LambdaQueryWrapperPlus;
import com.limyel.haoyuan.module.system.dept.dataobject.SysPostDO;
import com.limyel.haoyuan.module.system.dept.dto.SysPostFilterDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysPostDao extends BaseDao<SysPostDO> {

    default List<SysPostDO> selectList(SysPostFilterDTO req) {
        return selectList(new LambdaQueryWrapperPlus<SysPostDO>()
                .likeIfPresent(SysPostDO::getName, req.getName())
                .eqIfPresent(SysPostDO::getStatus, req.getStatus())
                .orderByAsc(SysPostDO::getSort));
    }

    default SysPostDO selectByNameAndCode(String name, String code) {
        return selectOne(new LambdaQueryWrapperPlus<SysPostDO>()
                .eqIfPresent(SysPostDO::getName, name)
                .eqIfPresent(SysPostDO::getCode, code));
    }

}
