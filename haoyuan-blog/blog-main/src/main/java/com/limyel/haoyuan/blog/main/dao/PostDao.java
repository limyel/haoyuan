package com.limyel.haoyuan.blog.main.dao;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.limyel.haoyuan.blog.main.entity.PostEntity;
import com.limyel.haoyuan.common.mybatis.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostDao extends BaseDao<PostEntity> {

    default int increaseViewNum(Long id) {
        LambdaUpdateWrapper<PostEntity> wrapper = new LambdaUpdateWrapper<PostEntity>()
                .eq(PostEntity::getId, id)
                .setSql("view_num = view_num + 1");
        return update(wrapper);
    }



}