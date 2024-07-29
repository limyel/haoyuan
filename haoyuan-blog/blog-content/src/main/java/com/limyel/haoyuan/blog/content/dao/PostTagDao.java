package com.limyel.haoyuan.blog.content.dao;

import com.limyel.haoyuan.blog.content.domain.PostTagEntity;
import com.limyel.haoyuan.common.mybatis.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostTagDao extends BaseDao<PostTagEntity> {

    int deleteByPostId(Long postId);

    List<Long> selectPostIdByTagIds(@Param("tagIds") List<Long> tagIds);

}