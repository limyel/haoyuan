package com.limyel.haoyuan.blog.main.dao;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.limyel.haoyuan.blog.main.domain.PostDO;
import com.limyel.haoyuan.common.mybatis.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostDao extends BaseDao<PostDO> {

    default int increaseViewNum(Long id) {
        LambdaUpdateWrapper<PostDO> wrapper = new LambdaUpdateWrapper<PostDO>()
                .eq(PostDO::getId, id)
                .setSql("view_num = view_num + 1");
        return update(wrapper);
    }



}