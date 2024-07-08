package com.limyel.haoyuan.blog.wiki.service;

import com.baomidou.mybatisplus.extension.parser.JsqlParserFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.limyel.haoyuan.blog.main.domain.PostDO;
import com.limyel.haoyuan.blog.wiki.convert.CoverConvert;
import com.limyel.haoyuan.blog.wiki.dao.CoverDao;
import com.limyel.haoyuan.blog.wiki.domain.CatalogDO;
import com.limyel.haoyuan.blog.wiki.domain.CoverDO;
import com.limyel.haoyuan.blog.wiki.dto.cover.CoverDTO;
import com.limyel.haoyuan.blog.wiki.dto.cover.CoverPageDTO;
import com.limyel.haoyuan.blog.wiki.exception.WikiErrorCode;
import com.limyel.haoyuan.blog.wiki.vo.cover.CoverPageVO;
import com.limyel.haoyuan.common.core.exception.BizException;
import com.limyel.haoyuan.common.mybatis.pojo.PageData;
import com.limyel.haoyuan.common.mybatis.query.LambdaQueryWrapperPlus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoverService {

    private final CoverDao coverDao;

    private final CatalogService catalogService;

    @Transactional(rollbackFor = Exception.class)
    public int create(CoverDTO dto) {
        coverDao.validateUnique(null, CoverDO::getTitle, dto.getTitle(), WikiErrorCode.COVER_SLUG_DUPLICATE);
        coverDao.validateUnique(null, CoverDO::getSlug, dto.getSlug(), WikiErrorCode.COVER_SLUG_DUPLICATE);

        CoverDO coverDO = CoverConvert.INSTANCE.toEntity(dto);
        return coverDao.insert(coverDO);
    }

    @Transactional(rollbackFor = Exception.class)
    public int delete(Long id) {
        coverDao.validateExist(id, WikiErrorCode.COVER_NOT_FOUND);

        List<CatalogDO> catalogDOList = catalogService.getByWikiId(id);
        if (catalogDOList.size() > 0) {
            throw new BizException(WikiErrorCode.COVER_HAS_CATALOG);
        }

        return coverDao.deleteById(id);
    }

    public PageData<CoverPageVO> getPage(CoverPageDTO dto) {
        Page<CoverDO> page = new Page<>(dto.getPageNum(), dto.getPageSize());
        LambdaQueryWrapperPlus<CoverDO> wrapperPlus = new LambdaQueryWrapperPlus<CoverDO>()
                .likeIfPresent(CoverDO::getTitle, dto.getTitle())
                .geIfPresent(CoverDO::getCreateTime, dto.getStartTime())
                .leIfPresent(CoverDO::getCreateTime, dto.getEndTime());
        coverDao.selectPage(page, wrapperPlus);
        List<CoverPageVO> list = CoverConvert.INSTANCE.toPageVO(page.getRecords());
        return new PageData<>(page, list);
    }

}
