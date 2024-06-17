package com.limyel.haoyuan.blog.main.convert;

import com.limyel.haoyuan.blog.main.domain.PostDO;
import com.limyel.haoyuan.blog.main.domain.TagDO;
import com.limyel.haoyuan.blog.main.dto.post.PostDTO;
import com.limyel.haoyuan.blog.main.dto.tag.TagDTO;
import com.limyel.haoyuan.blog.main.vo.tag.TagPageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TagConvert {

    TagConvert INSTANCE = Mappers.getMapper(TagConvert.class);

    TagDO toEntity(TagDTO dto);

    List<TagPageVO> toPageVO(List<TagDO> tagDOList);

}