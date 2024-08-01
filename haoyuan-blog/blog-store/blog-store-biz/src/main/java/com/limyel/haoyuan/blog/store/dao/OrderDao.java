package com.limyel.haoyuan.blog.store.dao;

import com.limyel.haoyuan.blog.store.entity.OrderEntity;
import com.limyel.haoyuan.common.mybatis.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderDao extends BaseDao<OrderEntity> {

}