package com.limyel.haoyuan.blog.main.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.limyel.haoyuan.blog.main.convert.TagConvert;
import com.limyel.haoyuan.blog.main.dao.TagDao;
import com.limyel.haoyuan.blog.main.domain.TagDO;
import com.limyel.haoyuan.blog.main.dto.tag.TagDTO;
import com.limyel.haoyuan.blog.main.dto.tag.TagPageDTO;
import com.limyel.haoyuan.blog.main.exception.MainErrorCode;
import com.limyel.haoyuan.blog.main.vo.tag.TagPageVO;
import com.limyel.haoyuan.common.mybatis.pojo.PageData;
import com.limyel.haoyuan.common.mybatis.query.LambdaQueryWrapperPlus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagDao tagDao;

    public int create(TagDTO dto) {
        validateNameUnique(null, dto.getName());
        validateSlugUnique(null, dto.getSlug());

        TagDO tagDO = TagConvert.INSTANCE.toEntity(dto);
        return tagDao.insert(tagDO);
    }

    public PageData<TagPageVO> getPage(TagPageDTO dto) {
        Page<TagDO> page = new Page<>(dto.getPageNum(), dto.getPageSize());
        LambdaQueryWrapperPlus<TagDO> wrapperPlus = new LambdaQueryWrapperPlus<TagDO>()
                .likeIfPresent(TagDO::getName, dto.getName())
                .geIfPresent(TagDO::getCreateTime, dto.getStartTime())
                .leIfPresent(TagDO::getCreateTime, dto.getEndTime());
        tagDao.selectPage(page, wrapperPlus);

        return new PageData<>(page, TagConvert.INSTANCE.toPageVO(page.getRecords()));
    }

    private void validateNameUnique(Long id, String name) {
        TagDO tagDO = tagDao.selectOne(TagDO::getName, name);
        if (tagDO != null) {
            tagDO.validateUnique(id, MainErrorCode.TAG_NAME_DUPLICATE);
        }
    }

    private void validateSlugUnique(Long id, String slug) {
        TagDO tagDO = tagDao.selectOne(TagDO::getSlug, slug);
        if (tagDO != null) {
            tagDO.validateUnique(id, MainErrorCode.TAG_SLUG_DUPLICATE);
        }
    }

}
