package com.limyel.haoyuan.system.dao;

import com.limyel.haoyuan.common.mybatis.dao.BaseDao;
import com.limyel.haoyuan.common.mybatis.query.LambdaQueryWrapperPlus;
import com.limyel.haoyuan.system.domain.DeptDO;
import com.limyel.haoyuan.system.dto.dept.DeptPageDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeptDao extends BaseDao<DeptDO> {

    default List<DeptDO> selectList(DeptPageDTO req) {
        return selectList(new LambdaQueryWrapperPlus<DeptDO>()
                .likeIfPresent(DeptDO::getName, req.getName())
                .eqIfPresent(DeptDO::getStatus, req.getStatus())
                .orderByAsc(DeptDO::getSort));
    }

    default Long selectCountByPid(Long pid) {
        return selectCount(DeptDO::getPid, pid);
    }

    default DeptDO selectByPidAndName(Long pid, String name) {
        LambdaQueryWrapperPlus<DeptDO> wrapperPlus = new LambdaQueryWrapperPlus<>();
        wrapperPlus.eqIfPresent(DeptDO::getPid, pid);
        wrapperPlus.eqIfPresent(DeptDO::getName, name);
        return selectOne(wrapperPlus);
    }

}