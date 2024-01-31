package com.limyel.haoyuan.module.system.dao.dict;

import com.limyel.haoyuan.framework.mybatis.dao.BaseDao;
import com.limyel.haoyuan.framework.mybatis.query.LambdaQueryWrapperPlus;
import com.limyel.haoyuan.module.system.dataobject.dept.SysPostDO;
import com.limyel.haoyuan.module.system.dataobject.dict.SysDictDataDO;
import com.limyel.haoyuan.module.system.dto.dept.SysPostFilterDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysDictDataDao extends BaseDao<SysDictDataDO> {

}
