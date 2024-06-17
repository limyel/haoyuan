package com.limyel.haoyuan.blog.main.dao;

import com.limyel.haoyuan.blog.main.domain.PostDO;
import com.limyel.haoyuan.common.mybatis.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostDao extends BaseDao<PostDO> {

}