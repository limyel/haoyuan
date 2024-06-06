package com.limyel.haoyuan.system.dao;

import com.limyel.haoyuan.common.mybatis.dao.BaseDao;
import com.limyel.haoyuan.common.mybatis.query.LambdaQueryWrapperPlus;
import com.limyel.haoyuan.system.domain.PostDO;
import com.limyel.haoyuan.system.dto.post.PostPageDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostDao extends BaseDao<PostDO> {

    default List<PostDO> selectList(PostPageDTO req) {
        return selectList(new LambdaQueryWrapperPlus<PostDO>()
                .likeIfPresent(PostDO::getName, req.getName())
                .eqIfPresent(PostDO::getStatus, req.getStatus())
                .orderByAsc(PostDO::getSort));
    }

    default PostDO selectByNameAndCode(String name, String code) {
        return selectOne(new LambdaQueryWrapperPlus<PostDO>()
                .eqIfPresent(PostDO::getName, name)
                .eqIfPresent(PostDO::getCode, code));
    }

}