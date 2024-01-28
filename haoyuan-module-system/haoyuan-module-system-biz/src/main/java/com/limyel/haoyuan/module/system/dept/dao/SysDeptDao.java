package com.limyel.haoyuan.module.system.dept.dao;

import com.limyel.haoyuan.framework.mybatis.dao.BaseDao;
import com.limyel.haoyuan.framework.mybatis.query.LambdaQueryWrapperPlus;
import com.limyel.haoyuan.module.system.dept.dataobject.SysDeptDO;
import com.limyel.haoyuan.module.system.dept.dto.SysDeptFilterDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysDeptDao extends BaseDao<SysDeptDO> {

    default List<SysDeptDO> selectList(SysDeptFilterDTO req) {
        return selectList(new LambdaQueryWrapperPlus<SysDeptDO>()
                .likeIfPresent(SysDeptDO::getName, req.getName())
                .eqIfPresent(SysDeptDO::getStatus, req.getStatus())
                .orderByAsc(SysDeptDO::getSort));
    }

    default Long selectCountByPid(Long pid) {
        return selectCount(SysDeptDO::getPid, pid);
    }

    default SysDeptDO selectByPidAndName(Long pid, String name) {
        LambdaQueryWrapperPlus<SysDeptDO> wrapperPlus = new LambdaQueryWrapperPlus<>();
        wrapperPlus.eqIfPresent(SysDeptDO::getPid, pid);
        wrapperPlus.eqIfPresent(SysDeptDO::getName, name);
        return selectOne(wrapperPlus);
    }

}
