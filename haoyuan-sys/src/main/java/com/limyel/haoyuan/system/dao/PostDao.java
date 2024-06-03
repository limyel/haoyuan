package com.limyel.haoyuan.system.dao;

import com.limyel.haoyuan.common.mybatis.dao.BaseDao;
import com.limyel.haoyuan.common.mybatis.query.LambdaQueryWrapperPlus;
import com.limyel.haoyuan.system.entity.PostEntity;
import com.limyel.haoyuan.system.dto.post.PostPageDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostDao extends BaseDao<PostEntity> {

    default List<PostEntity> selectList(PostPageDTO req) {
        return selectList(new LambdaQueryWrapperPlus<PostEntity>()
                .likeIfPresent(PostEntity::getName, req.getName())
                .eqIfPresent(PostEntity::getStatus, req.getStatus())
                .orderByAsc(PostEntity::getSort));
    }

    default PostEntity selectByNameAndCode(String name, String code) {
        return selectOne(new LambdaQueryWrapperPlus<PostEntity>()
                .eqIfPresent(PostEntity::getName, name)
                .eqIfPresent(PostEntity::getCode, code));
    }

}