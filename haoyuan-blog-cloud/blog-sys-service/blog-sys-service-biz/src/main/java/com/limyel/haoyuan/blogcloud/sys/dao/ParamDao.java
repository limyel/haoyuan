package com.limyel.haoyuan.blogcloud.sys.dao;

import com.limyel.haoyuan.blogcloud.sys.entity.ParamEntity;
import com.limyel.haoyuan.common.mybatis.dao.BaseDao;
import com.limyel.haoyuan.common.mybatis.query.LambdaQueryWrapperPlus;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ParamDao extends BaseDao<ParamEntity> {

    default ParamEntity selectByCode(String code) {
        return selectOne(new LambdaQueryWrapperPlus<ParamEntity>()
                .eqIfPresent(ParamEntity::getCode, code));
    }

}