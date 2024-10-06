package com.limyel.haoyuan.mall.common.member.convert;

import com.limyel.haoyuan.mall.common.member.dto.level.LevelDTO;
import com.limyel.haoyuan.mall.common.member.dto.level.api.LevelInfo;
import com.limyel.haoyuan.mall.common.member.entity.LevelEntity;
import com.limyel.haoyuan.mall.common.member.vo.level.LevelPageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LevelConvert {

    LevelConvert INSTANCE = Mappers.getMapper(LevelConvert.class);

    LevelDTO toDTO(LevelEntity entity);

    LevelEntity toEntity(LevelDTO dto);

    LevelPageVO toPageVO(LevelEntity entity);

    LevelInfo toInfo(LevelDTO dto);

}