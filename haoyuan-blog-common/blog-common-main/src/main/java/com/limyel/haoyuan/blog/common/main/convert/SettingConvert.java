package com.limyel.haoyuan.blog.common.main.convert;

import com.limyel.haoyuan.blog.common.main.dto.setting.SettingDTO;
import com.limyel.haoyuan.blog.common.main.entity.SettingEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SettingConvert {

    SettingConvert INSTANCE = Mappers.getMapper(SettingConvert.class);

    SettingEntity toDO(SettingDTO dto);

    SettingDTO toDTO(SettingEntity entity);


}