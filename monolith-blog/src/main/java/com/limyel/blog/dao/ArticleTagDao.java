package com.limyel.blog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.limyel.blog.model.entity.ArticleTagEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleTagDao extends BaseMapper<ArticleTagEntity> {

    List<ArticleTagEntity> findByArticleId(Long articleId);

    List<ArticleTagEntity> findByTagId(Long tagId);

    int deleteByArticleId(Long articleId);

}
