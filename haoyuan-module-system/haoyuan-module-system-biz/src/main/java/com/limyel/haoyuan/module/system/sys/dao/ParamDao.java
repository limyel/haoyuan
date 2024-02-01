package com.limyel.haoyuan.module.system.sys.dao;

import com.limyel.haoyuan.framework.mybatis.dao.BaseDao;
import com.limyel.haoyuan.framework.mybatis.query.LambdaQueryWrapperPlus;
import com.limyel.haoyuan.module.system.sys.dataobject.ParamDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ParamDao extends BaseDao<ParamDO> {

    default ParamDO selectByCode(String code) {
        return selectOne(new LambdaQueryWrapperPlus<ParamDO>()
                .eqIfPresent(ParamDO::getCode, code));
    }

}
