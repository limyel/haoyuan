package com.limyel.haoyuan.blog.dao;

import com.limyel.haoyuan.blog.entity.PostEntity;
import com.limyel.haoyuan.common.mybatis.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostDao extends BaseDao<PostEntity> {

}