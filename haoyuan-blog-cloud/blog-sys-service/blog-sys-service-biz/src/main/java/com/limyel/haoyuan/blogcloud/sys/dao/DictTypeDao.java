package com.limyel.haoyuan.blogcloud.sys.dao;

import com.limyel.haoyuan.blogcloud.sys.entity.DictTypeEntity;
import com.limyel.haoyuan.common.mybatis.dao.BaseDao;
import com.limyel.haoyuan.common.mybatis.query.LambdaQueryWrapperPlus;
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