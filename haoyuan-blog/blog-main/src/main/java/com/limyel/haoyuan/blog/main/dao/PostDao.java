package com.limyel.haoyuan.blog.main.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.limyel.haoyuan.blog.main.domain.PostDO;
import com.limyel.haoyuan.blog.main.dto.post.PostFilterDTO;
import com.limyel.haoyuan.common.mybatis.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostDao extends BaseDao<PostDO> {

    IPage<PostDO> selectPage(IPage<PostDO> page, PostFilterDTO dto);

}