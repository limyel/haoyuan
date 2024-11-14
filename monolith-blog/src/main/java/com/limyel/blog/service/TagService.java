package com.limyel.blog.service;

import com.limyel.blog.dao.ArticleTagDao;
import com.limyel.blog.dao.TagDao;
import com.limyel.blog.model.dto.TagDTO;
import com.limyel.blog.model.entity.ArticleTagEntity;
import com.limyel.blog.model.entity.TagEntity;
import com.limyel.blog.model.vo.TagListVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagDao tagDao;

    private final ArticleTagDao articleTagDao;

    public void create(TagDTO dto) {
        TagEntity entity = new TagEntity();
        BeanUtils.copyProperties(dto, entity);
        tagDao.save(entity);
    }

    public void update(TagDTO dto) {
        TagEntity entity = tagDao.findById(dto.getId()).orElseThrow(() -> new RuntimeException("标签不存在"));
        BeanUtils.copyProperties(dto, entity);
        tagDao.save(entity);
    }

    public TagDTO get(Long id) {
        return tagDao.findById(id).map(tag -> {
            TagDTO dto = new TagDTO();
            BeanUtils.copyProperties(tag, dto);
            return dto;
        }).orElseThrow(() -> new RuntimeException("标签不存在"));
    }

    public TagEntity get(String slug) {
        return tagDao.findBySlug(slug);
    }

    public List<TagListVO> list() {
        List<TagListVO> result = new ArrayList<>();

        List<TagEntity> tags = tagDao.findAll();
        for (TagEntity tag : tags) {
            TagListVO tagListVO = new TagListVO();
            BeanUtils.copyProperties(tag, tagListVO);
            result.add(tagListVO);
        }

        return result;
    }

    public Page<TagEntity> page(int pageNum, int pageSize) {
        PageRequest pageable = PageRequest.of(pageNum, pageSize);
        return tagDao.findAll(pageable);
    }

    public List<TagEntity> listAll() {
        return tagDao.findAll();
    }

    public List<TagListVO> listByArticle(Long articleId) {
        List<Long> tagIds = articleTagDao.findByArticleId(articleId).stream()
                .map(ArticleTagEntity::getTagId)
                .toList();
        if (tagIds.isEmpty()) {
            return List.of();
        }

        List<TagEntity> tags = tagDao.findByIdIn(tagIds);
        return tags.stream().map(tag -> {
            TagListVO tagListVO = new TagListVO();
            BeanUtils.copyProperties(tag, tagListVO);
            return tagListVO;
        }).toList();
    }

}
