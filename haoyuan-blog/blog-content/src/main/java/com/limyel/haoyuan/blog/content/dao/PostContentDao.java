package com.limyel.haoyuan.blog.content.dao;

import com.limyel.haoyuan.blog.content.entity.PostContentEntity;
import com.limyel.haoyuan.common.mybatis.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostContentDao extends BaseDao<PostContentEntity> {

}