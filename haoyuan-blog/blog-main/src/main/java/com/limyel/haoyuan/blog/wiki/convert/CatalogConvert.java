package com.limyel.haoyuan.blog.wiki.convert;

import com.limyel.haoyuan.blog.wiki.domain.CatalogDO;
import com.limyel.haoyuan.blog.wiki.domain.CoverDO;
import com.limyel.haoyuan.blog.wiki.dto.catalog.CatalogDTO;
import com.limyel.haoyuan.blog.wiki.dto.cover.CoverDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CatalogConvert {

    CatalogConvert INSTANCE = Mappers.getMapper(CatalogConvert.class);

    CatalogDO toEntity(CatalogDTO dto);

}