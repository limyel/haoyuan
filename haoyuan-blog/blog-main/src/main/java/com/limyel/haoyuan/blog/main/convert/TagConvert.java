package com.limyel.haoyuan.blog.main.convert;

import com.limyel.haoyuan.blog.main.entity.TagEntity;
import com.limyel.haoyuan.blog.main.dto.tag.TagDTO;
import com.limyel.haoyuan.blog.main.vo.tag.TagDetailVO;
import com.limyel.haoyuan.blog.main.vo.tag.TagPageVO;
import com.limyel.haoyuan.blog.main.vo.tag.TagPostVO;
import com.limyel.haoyuan.blog.main.vo.tag.TagSelectVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TagConvert {

    TagConvert INSTANCE = Mappers.getMapper(TagConvert.class);

    TagEntity toEntity(TagDTO dto);

    List<TagPageVO> toPageVO(List<TagEntity> tagDOList);

    List<TagSelectVO> toSelectVO(List<TagEntity> tagDOList);

    TagPostVO toPostVO(TagEntity tagDO);

    TagDetailVO toDetailVO(TagEntity tagDO);

}