package com.limyel.haoyuan.blog.main.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.limyel.haoyuan.blog.common.main.constant.MainErrorMsg;
import com.limyel.haoyuan.blog.common.main.constant.MainRedisKey;
import com.limyel.haoyuan.blog.common.main.convert.TagConvert;
import com.limyel.haoyuan.blog.common.main.dto.tag.TagDTO;
import com.limyel.haoyuan.blog.common.main.dto.tag.TagPageDTO;
import com.limyel.haoyuan.blog.common.main.entity.TagEntity;
import com.limyel.haoyuan.blog.common.main.vo.tag.TagDetailVO;
import com.limyel.haoyuan.blog.common.main.vo.tag.TagPageVO;
import com.limyel.haoyuan.blog.common.main.vo.tag.TagSelectVO;
import com.limyel.haoyuan.blog.main.dao.TagDao;
import com.limyel.haoyuan.common.core.util.JSONUtil;
import com.limyel.haoyuan.common.mybatis.pojo.PageData;
import com.limyel.haoyuan.common.mybatis.query.LambdaQueryWrapperPlus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagDao tagDao;

    private final PostTagService postTagService;

    private final StringRedisTemplate redisTemplate;

    public int create(TagDTO dto) {
        tagDao.validateUnique(null, TagEntity::getName, dto.getName(), MainErrorMsg.TAG_NAME_DUPLICATE);
        tagDao.validateUnique(null, TagEntity::getSlug, dto.getSlug(), MainErrorMsg.TAG_SLUG_DUPLICATE);

        TagEntity tagDO = TagConvert.INSTANCE.toEntity(dto);

        return tagDao.insert(tagDO);
    }

    public int delete(String slug) {
        return tagDao.delete(TagEntity::getSlug, slug);
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
        List<TagDetailVO> result = tagDOList.stream()
                .map(tagDO -> {
                    TagDetailVO vo = TagConvert.INSTANCE.toDetailVO(tagDO);
                    vo.setPostNum(postTagService.getPostNumByTagId(tagDO.getId()));
                    return vo;
                })
                .toList();
        redisTemplate.opsForValue().set(MainRedisKey.TAG_DETAIL_KEY, JSONUtil.toJson(result), 12, TimeUnit.HOURS);
        return result;
    }

    public Long getCount() {
        return tagDao.selectCount();
    }

}
