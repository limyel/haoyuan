package com.limyel.haoyuan.system.dao;

import com.limyel.haoyuan.common.mybatis.dao.BaseDao;
import com.limyel.haoyuan.common.mybatis.query.LambdaQueryWrapperPlus;
import com.limyel.haoyuan.system.domain.ParamDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ParamDao extends BaseDao<ParamDO> {

    default ParamDO selectByCode(String code) {
        return selectOne(new LambdaQueryWrapperPlus<ParamDO>()
                .eqIfPresent(ParamDO::getCode, code));
    }

}