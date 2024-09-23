package com.limyel.haoyuan.blog.common.main.convert;

import com.limyel.haoyuan.blog.common.main.dto.post.PostDTO;
import com.limyel.haoyuan.blog.common.main.entity.PostEntity;
import com.limyel.haoyuan.blog.common.main.vo.post.PostArchiveVO;
import com.limyel.haoyuan.blog.common.main.vo.post.PostDetailVO;
import com.limyel.haoyuan.blog.common.main.vo.post.PostListVO;
import com.limyel.haoyuan.blog.common.main.vo.post.PostPageVO;
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