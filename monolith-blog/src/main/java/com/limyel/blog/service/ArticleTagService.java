package com.limyel.blog.service;

import com.limyel.blog.dao.ArticleTagDao;
import com.limyel.blog.model.entity.ArticleTagEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleTagService {

    private final ArticleTagDao articleTagDao;

    @Transactional
    public int create(Long articleId, List<Long> tagIds) {
        return articleTagDao.insert(tagIds.stream().map(tagId -> {
            ArticleTagEntity articleTagEntity = new ArticleTagEntity();
            articleTagEntity.setArticleId(articleId);
            articleTagEntity.setTagId(tagId);
            return articleTagEntity;
        }).toList()).size();
    }

    @Transactional
    public void deleteByArticle(Long articleId) {
        articleTagDao.deleteByArticleId(articleId);
    }

    public List<ArticleTagEntity> listByArticle(Long articleId) {
        return articleTagDao.findByArticleId(articleId);
    }

    public List<ArticleTagEntity> listByTag(Long tagId) {
        return articleTagDao.findByTagId(tagId);
    }

    public List<Long> listArticleId(Long tagId) {
        List<ArticleTagEntity> list = listByTag(tagId);
        return list.stream()
                .map(ArticleTagEntity::getArticleId)
                .toList();
    }

}
