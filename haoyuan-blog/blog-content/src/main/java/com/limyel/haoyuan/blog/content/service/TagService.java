package com.limyel.haoyuan.blog.content.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.limyel.haoyuan.blog.content.convert.TagConvert;
import com.limyel.haoyuan.blog.content.dao.TagDao;
import com.limyel.haoyuan.blog.content.entity.TagEntity;
import com.limyel.haoyuan.blog.content.dto.tag.TagDTO;
import com.limyel.haoyuan.blog.content.dto.tag.TagPageDTO;
import com.limyel.haoyuan.blog.content.exception.MainErrorCode;
import com.limyel.haoyuan.blog.content.vo.tag.TagDetailVO;
import com.limyel.haoyuan.blog.content.vo.tag.TagPageVO;
import com.limyel.haoyuan.blog.content.vo.tag.TagSelectVO;
import com.limyel.haoyuan.common.mybatis.pojo.PageData;
import com.limyel.haoyuan.common.mybatis.query.LambdaQueryWrapperPlus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagDao tagDao;

    private final PostTagService postTagService;

    public int create(TagDTO dto) {
        tagDao.validateUnique(null, TagEntity::getName, dto.getName(), MainErrorCode.TAG_NAME_DUPLICATE);
        tagDao.validateUnique(null, TagEntity::getSlug, dto.getSlug(), MainErrorCode.TAG_SLUG_DUPLICATE);

        TagEntity tagDO = TagConvert.INSTANCE.toEntity(dto);
        return tagDao.insert(tagDO);
    }

    public void delete(String slug) {
        tagDao.delete(TagEntity::getSlug, slug);
    }

    public PageData<TagPageVO> getPage(TagPageDTO dto) {
        Page<TagEntity> page = new Page<>(dto.getPageNum(), dto.getPageSize());
        LambdaQueryWrapperPlus<TagEntity> wrapperPlus = new LambdaQueryWrapperPlus<TagEntity>()
                .likeIfPresent(TagEntity::getName, dto.getName())
                .geIfPresent(TagEntity::getCreateTime, dto.getStartTime())
                .leIfPresent(TagEntity::getCreateTime, dto.getEndTime());
        tagDao.selectPage(page, wrapperPlus);

        return new PageData<>(page, TagConvert.INSTANCE.toPageVO(page.getRecords()));
    }

    public List<TagSelectVO> getSelect() {
        List<TagEntity> tagDOList = tagDao.selectList();
        return TagConvert.INSTANCE.toSelectVO(tagDOList);
    }

    public List<TagDetailVO> getAll() {
        List<TagEntity> tagDOList = tagDao.selectList();
        return tagDOList.stream()
                .map(tagDO -> {
                    TagDetailVO vo = TagConvert.INSTANCE.toDetailVO(tagDO);
                    vo.setPostNum(postTagService.getPostNumByTagId(tagDO.getId()));
                    return vo;
                })
                .toList();
    }

    public Long getCount() {
        return tagDao.selectCount();
    }

}
