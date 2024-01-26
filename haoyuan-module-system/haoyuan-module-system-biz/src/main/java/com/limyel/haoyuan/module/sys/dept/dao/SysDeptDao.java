package com.limyel.haoyuan.module.sys.dept.dao;

import com.limyel.haoyuan.framework.mybatis.dao.BaseDao;
import com.limyel.haoyuan.framework.mybatis.query.LambdaQueryWrapperPlus;
import com.limyel.haoyuan.module.sys.dept.dto.SysDeptFilterDTO;
import com.limyel.haoyuan.module.sys.dept.entity.SysDeptEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysDeptDao extends BaseDao<SysDeptEntity> {

    default List<SysDeptEntity> selectList(SysDeptFilterDTO req) {
        return selectList(new LambdaQueryWrapperPlus<SysDeptEntity>()
                .likeIfPresent(SysDeptEntity::getName, req.getName())
                .eqIfPresent(SysDeptEntity::getStatus, req.getStatus())
                .orderByAsc(SysDeptEntity::getSort));
    }

    default Long selectCountByPid(Long pid) {
        return selectCount(SysDeptEntity::getPid, pid);
    }

    default SysDeptEntity selectByPidAndName(Long pid, String name) {
        LambdaQueryWrapperPlus<SysDeptEntity> wrapperPlus = new LambdaQueryWrapperPlus<>();
        wrapperPlus.eqIfPresent(SysDeptEntity::getPid, pid);
        wrapperPlus.eqIfPresent(SysDeptEntity::getName, name);
        return selectOne(wrapperPlus);
    }

}
