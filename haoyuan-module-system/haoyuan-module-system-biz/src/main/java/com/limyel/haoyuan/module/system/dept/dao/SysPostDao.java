package com.limyel.haoyuan.module.system.dept.dao;

import com.limyel.haoyuan.framework.mybatis.dao.BaseDao;
import com.limyel.haoyuan.framework.mybatis.query.LambdaQueryWrapperPlus;
import com.limyel.haoyuan.module.system.dept.dto.SysPostFilterDTO;
import com.limyel.haoyuan.module.system.dept.entity.SysPostEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysPostDao extends BaseDao<SysPostEntity> {

    default List<SysPostEntity> selectList(SysPostFilterDTO req) {
        return selectList(new LambdaQueryWrapperPlus<SysPostEntity>()
                .likeIfPresent(SysPostEntity::getName, req.getName())
                .eqIfPresent(SysPostEntity::getStatus, req.getStatus())
                .orderByAsc(SysPostEntity::getSort));
    }

    default SysPostEntity selectByNameAndCode(String name, String code) {
        return selectOne(new LambdaQueryWrapperPlus<SysPostEntity>()
                .eqIfPresent(SysPostEntity::getName, name)
                .eqIfPresent(SysPostEntity::getCode, code));
    }

}
