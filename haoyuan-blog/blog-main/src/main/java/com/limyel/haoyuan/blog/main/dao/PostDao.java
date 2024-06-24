package com.limyel.haoyuan.blog.main.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.limyel.haoyuan.blog.main.domain.PostDO;
import com.limyel.haoyuan.blog.main.dto.post.PostListDTO;
import com.limyel.haoyuan.common.mybatis.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PostDao extends BaseDao<PostDO> {

}