package com.limyel.haoyuan.mall.member.convert;

import com.limyel.haoyuan.mall.member.dto.level.LevelInfo;
import com.limyel.haoyuan.mall.member.dto.level.LevelDTO;
import com.limyel.haoyuan.mall.member.entity.LevelEntity;
import com.limyel.haoyuan.mall.member.vo.level.LevelPageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LevelConvert {

    LevelConvert INSTANCE = Mappers.getMapper(LevelConvert.class);

    LevelEntity toEntity(LevelDTO dto);

    LevelPageVO toPageVO(LevelEntity level);

    LevelDTO toDTO(LevelEntity entity);

    LevelInfo toInfo(LevelEntity entity);

}