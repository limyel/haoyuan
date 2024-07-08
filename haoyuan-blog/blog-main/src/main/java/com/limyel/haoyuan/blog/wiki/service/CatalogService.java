package com.limyel.haoyuan.blog.wiki.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.limyel.haoyuan.blog.wiki.convert.CatalogConvert;
import com.limyel.haoyuan.blog.wiki.convert.CoverConvert;
import com.limyel.haoyuan.blog.wiki.dao.CatalogDao;
import com.limyel.haoyuan.blog.wiki.dao.CoverDao;
import com.limyel.haoyuan.blog.wiki.domain.CatalogDO;
import com.limyel.haoyuan.blog.wiki.domain.CoverDO;
import com.limyel.haoyuan.blog.wiki.dto.catalog.CatalogDTO;
import com.limyel.haoyuan.blog.wiki.dto.cover.CoverDTO;
import com.limyel.haoyuan.blog.wiki.exception.WikiErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CatalogService {

    private final CatalogDao catalogDao;

    public int create(CatalogDTO dto) {
        // todo 校验

        CatalogDO catalogDO = CatalogConvert.INSTANCE.toEntity(dto);
        return catalogDao.insert(catalogDO);
    }

    public List<CatalogDO> getByWikiId(Long wikiId) {
        return catalogDao.selectList(new LambdaQueryWrapper<CatalogDO>()
                .eq(CatalogDO::getWikiId, wikiId));
    }

}
