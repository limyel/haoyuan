package com.limyel.haoyuan.blog.common.project.convert;

import com.limyel.haoyuan.blog.common.project.dto.project.ProjectDTO;
import com.limyel.haoyuan.blog.common.project.entity.ProjectEntity;
import com.limyel.haoyuan.blog.common.project.vo.project.ProjectListVO;
import com.limyel.haoyuan.blog.common.project.vo.project.ProjectPageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProjectConvert {

    ProjectConvert INSTANCE = Mappers.getMapper(ProjectConvert.class);

    ProjectEntity toEntity(ProjectDTO dto);

    ProjectDTO toDTO(ProjectEntity project);

    ProjectPageVO toPageVO(ProjectEntity project);

    ProjectListVO toListVO(ProjectEntity project);

}