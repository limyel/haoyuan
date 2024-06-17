package com.limyel.haoyuan.blog.main.dao;

import com.limyel.haoyuan.blog.main.domain.PostTagDO;
import com.limyel.haoyuan.common.mybatis.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostTagDao extends BaseDao<PostTagDO> {

    int deleteByPostId(Long postId);

}