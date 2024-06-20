package com.limyel.haoyuan.blog.main.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.limyel.haoyuan.blog.main.domain.TagDO;
import com.limyel.haoyuan.common.mybatis.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TagDao extends BaseDao<TagDO> {

    default List<TagDO> selectByIds(List<Long> ids) {
        return selectList(new LambdaQueryWrapper<TagDO>()
                .in(TagDO::getId, ids));
    }

}