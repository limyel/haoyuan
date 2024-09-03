package com.limyel.haoyuan.blog.main.dao;

import com.limyel.haoyuan.blog.main.entity.PostTagEntity;
import com.limyel.haoyuan.common.mybatis.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostTagDao extends BaseDao<PostTagEntity> {

    int deleteByPostId(Long postId);

    List<Long> selectPostIdByTagIds(@Param("tagSlugs") List<String> tagSlugs);

}