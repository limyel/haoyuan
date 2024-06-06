package com.limyel.haoyuan.system.dao;

import com.limyel.haoyuan.common.mybatis.dao.BaseDao;
import com.limyel.haoyuan.common.mybatis.query.LambdaQueryWrapperPlus;
import com.limyel.haoyuan.system.domain.DictTypeDO;
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