package com.limyel.haoyuan.system.dao;

import com.limyel.haoyuan.common.mybatis.dao.BaseDao;
import com.limyel.haoyuan.common.mybatis.query.LambdaQueryWrapperPlus;
import com.limyel.haoyuan.system.domain.DeptEntity;
import com.limyel.haoyuan.system.dto.dept.DeptPageDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeptDao extends BaseDao<DeptEntity> {

    default List<DeptEntity> selectList(DeptPageDTO req) {
        return selectList(new LambdaQueryWrapperPlus<DeptEntity>()
                .likeIfPresent(DeptEntity::getName, req.getName())
                .eqIfPresent(DeptEntity::getStatus, req.getStatus())
                .orderByAsc(DeptEntity::getSort));
    }

    default Long selectCountByPid(Long pid) {
        return selectCount(DeptEntity::getPid, pid);
    }

    default DeptEntity selectByPidAndName(Long pid, String name) {
        LambdaQueryWrapperPlus<DeptEntity> wrapperPlus = new LambdaQueryWrapperPlus<>();
        wrapperPlus.eqIfPresent(DeptEntity::getPid, pid);
        wrapperPlus.eqIfPresent(DeptEntity::getName, name);
        return selectOne(wrapperPlus);
    }

}