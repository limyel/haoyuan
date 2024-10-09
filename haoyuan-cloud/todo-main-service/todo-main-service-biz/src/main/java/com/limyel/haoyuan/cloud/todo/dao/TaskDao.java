package com.limyel.haoyuan.cloud.todo.dao;

import com.limyel.haoyuan.cloud.todo.entity.TaskEntity;
import com.limyel.haoyuan.common.mybatis.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TaskDao extends BaseDao<TaskEntity> {

}
