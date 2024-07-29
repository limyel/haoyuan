package com.limyel.haoyuan.blog.content.convert;

import com.limyel.haoyuan.blog.content.domain.PostEntity;
import com.limyel.haoyuan.blog.content.dto.post.PostDTO;
import com.limyel.haoyuan.blog.content.vo.post.PostArchiveVO;
import com.limyel.haoyuan.blog.content.vo.post.PostDetailVO;
import com.limyel.haoyuan.blog.content.vo.post.PostListVO;
import com.limyel.haoyuan.blog.content.vo.post.PostPageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PostConvert {

    PostConvert INSTANCE = Mappers.getMapper(PostConvert.class);

    PostEntity toEntity(PostDTO dto);

    List<PostPageVO> toPageVO(List<PostEntity> postDOList);

    PostDTO toDTO(PostEntity postDO);

    PostListVO toListVO(PostEntity postDO);

    PostDetailVO toDetailVO(PostEntity postDO);

    PostArchiveVO.Item toArciveVOItem(PostEntity postDO);

}