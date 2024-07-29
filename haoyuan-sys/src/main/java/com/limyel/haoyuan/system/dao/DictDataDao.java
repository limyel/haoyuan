package com.limyel.haoyuan.system.dao;

import com.limyel.haoyuan.common.mybatis.dao.BaseDao;
import com.limyel.haoyuan.common.mybatis.query.LambdaQueryWrapperPlus;
import com.limyel.haoyuan.system.domain.DictDataEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DictDataDao extends BaseDao<DictDataEntity> {

    default DictDataEntity selectByTypeAndValue(String type, String value) {
        return selectOne(new LambdaQueryWrapperPlus<DictDataEntity>()
                .eqIfPresent(DictDataEntity::getType, type)
                .eqIfPresent(DictDataEntity::getValue, value));
    }

}