package com.limyel.haoyuan.blog.sync.convert;

import com.limyel.haoyuan.blog.sync.dto.PointDTO;
import com.limyel.haoyuan.blog.sync.entity.RecordEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RecordConvert {

    RecordConvert INSTANCE = Mappers.getMapper(RecordConvert.class);

    PointDTO toPointDTO(RecordEntity entity);

}