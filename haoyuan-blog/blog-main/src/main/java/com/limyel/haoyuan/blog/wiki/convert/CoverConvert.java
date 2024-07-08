package com.limyel.haoyuan.blog.wiki.convert;

import com.limyel.haoyuan.blog.main.domain.TagDO;
import com.limyel.haoyuan.blog.main.dto.tag.TagDTO;
import com.limyel.haoyuan.blog.main.vo.tag.TagDetailVO;
import com.limyel.haoyuan.blog.main.vo.tag.TagPageVO;
import com.limyel.haoyuan.blog.main.vo.tag.TagPostVO;
import com.limyel.haoyuan.blog.main.vo.tag.TagSelectVO;
import com.limyel.haoyuan.blog.wiki.domain.CoverDO;
import com.limyel.haoyuan.blog.wiki.dto.cover.CoverDTO;
import com.limyel.haoyuan.blog.wiki.vo.cover.CoverPageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CoverConvert {

    CoverConvert INSTANCE = Mappers.getMapper(CoverConvert.class);

    CoverDO toEntity(CoverDTO dto);

    List<CoverPageVO> toPageVO(List<CoverDO> list);

}