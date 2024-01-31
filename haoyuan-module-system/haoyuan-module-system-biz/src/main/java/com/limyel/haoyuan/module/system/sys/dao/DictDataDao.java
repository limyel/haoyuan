package com.limyel.haoyuan.module.system.sys.dao;

import com.limyel.haoyuan.framework.mybatis.dao.BaseDao;
import com.limyel.haoyuan.framework.mybatis.query.LambdaQueryWrapperPlus;
import com.limyel.haoyuan.module.system.sys.dataobject.DictDataDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DictDataDao extends BaseDao<DictDataDO> {

    default DictDataDO selectByTypeAndValue(String type, String value) {
        return selectOne(new LambdaQueryWrapperPlus<DictDataDO>()
                .eqIfPresent(DictDataDO::getType, type)
                .eqIfPresent(DictDataDO::getValue, value));
    }

}
