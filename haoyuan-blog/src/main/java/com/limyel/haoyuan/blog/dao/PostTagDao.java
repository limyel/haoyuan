package com.limyel.haoyuan.blog.dao;

import com.limyel.haoyuan.blog.entity.PostTagEntity;
import com.limyel.haoyuan.common.mybatis.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostTagDao extends BaseDao<PostTagEntity> {

    int deleteByPostId(Long postId);

}