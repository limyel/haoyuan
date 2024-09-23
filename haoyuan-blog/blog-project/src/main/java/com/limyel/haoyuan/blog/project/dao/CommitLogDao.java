package com.limyel.haoyuan.blog.project.dao;

import com.limyel.haoyuan.blog.common.project.entity.CommitLogEntity;
import com.limyel.haoyuan.common.mybatis.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommitLogDao extends BaseDao<CommitLogEntity> {
}
