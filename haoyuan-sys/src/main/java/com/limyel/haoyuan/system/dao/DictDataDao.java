package com.limyel.haoyuan.system.dao;

import com.limyel.haoyuan.common.mybatis.dao.BaseDao;
import com.limyel.haoyuan.common.mybatis.query.LambdaQueryWrapperPlus;
import com.limyel.haoyuan.system.domain.DictDataDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DictDataDao extends BaseDao<DictDataDO> {

    default DictDataDO selectByTypeAndValue(String type, String value) {
        return selectOne(new LambdaQueryWrapperPlus<DictDataDO>()
                .eqIfPresent(DictDataDO::getType, type)
                .eqIfPresent(DictDataDO::getValue, value));
    }

}