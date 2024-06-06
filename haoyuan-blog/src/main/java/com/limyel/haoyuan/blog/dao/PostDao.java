package com.limyel.haoyuan.blog.dao;

import com.limyel.haoyuan.blog.entity.PostDO;
import com.limyel.haoyuan.common.mybatis.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostDao extends BaseDao<PostDO> {

}