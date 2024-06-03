package com.limyel.haoyuan.system.dao;

import com.limyel.haoyuan.common.mybatis.dao.BaseDao;
import com.limyel.haoyuan.common.mybatis.query.LambdaQueryWrapperPlus;
import com.limyel.haoyuan.system.entity.DictTypeEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DictTypeDao extends BaseDao<DictTypeEntity> {

    default DictTypeEntity selectByName(String name) {
        return selectOne(new LambdaQueryWrapperPlus<DictTypeEntity>()
                .eqIfPresent(DictTypeEntity::getName, name));
    }

    default DictTypeEntity selectByType(String type) {
        return selectOne(new LambdaQueryWrapperPlus<DictTypeEntity>()
                .eqIfPresent(DictTypeEntity::getType, type));
    }

}