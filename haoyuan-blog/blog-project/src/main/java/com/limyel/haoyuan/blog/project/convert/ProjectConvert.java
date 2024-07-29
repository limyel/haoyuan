package com.limyel.haoyuan.blog.project.convert;

import com.limyel.haoyuan.blog.project.dto.project.ProjectDTO;
import com.limyel.haoyuan.blog.project.entity.ProjectEntity;
import com.limyel.haoyuan.blog.project.vo.project.ProjectListVO;
import com.limyel.haoyuan.blog.project.vo.project.ProjectPageVO;
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