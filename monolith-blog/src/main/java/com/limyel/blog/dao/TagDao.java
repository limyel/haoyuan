package com.limyel.blog.dao;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.limyel.blog.model.entity.TagEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TagDao extends BaseMapper<TagEntity> {

    List<TagEntity> findByIdIn(List<Long> ids);

    TagEntity findBySlug(String slug);

}
