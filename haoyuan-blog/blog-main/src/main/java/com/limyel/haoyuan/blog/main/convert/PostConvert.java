package com.limyel.haoyuan.blog.main.convert;

import com.limyel.haoyuan.blog.main.dto.post.PostDTO;
import com.limyel.haoyuan.blog.main.domain.PostDO;
import com.limyel.haoyuan.blog.main.vo.post.PostArchiveVO;
import com.limyel.haoyuan.blog.main.vo.post.PostDetailVO;
import com.limyel.haoyuan.blog.main.vo.post.PostListVO;
import com.limyel.haoyuan.blog.main.vo.post.PostPageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PostConvert {

    PostConvert INSTANCE = Mappers.getMapper(PostConvert.class);

    PostDO toEntity(PostDTO dto);

    List<PostPageVO> toPageVO(List<PostDO> postDOList);

    PostDTO toDTO(PostDO postDO);

    PostListVO toListVO(PostDO postDO);

    PostDetailVO toDetailVO(PostDO postDO);

    PostArchiveVO.Item toArciveVOItem(PostDO postDO);

}