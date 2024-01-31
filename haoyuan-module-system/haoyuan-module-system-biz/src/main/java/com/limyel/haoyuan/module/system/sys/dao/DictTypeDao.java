package com.limyel.haoyuan.module.system.sys.dao;

import com.limyel.haoyuan.framework.mybatis.dao.BaseDao;
import com.limyel.haoyuan.framework.mybatis.query.LambdaQueryWrapperPlus;
import com.limyel.haoyuan.module.system.sys.dataobject.DictTypeDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DictTypeDao extends BaseDao<DictTypeDO> {

    default DictTypeDO selectByName(String name) {
        return selectOne(new LambdaQueryWrapperPlus<DictTypeDO>()
                .eqIfPresent(DictTypeDO::getName, name));
    }

    default DictTypeDO selectByType(String type) {
        return selectOne(new LambdaQueryWrapperPlus<DictTypeDO>()
                .eqIfPresent(DictTypeDO::getType, type));
    }

}
