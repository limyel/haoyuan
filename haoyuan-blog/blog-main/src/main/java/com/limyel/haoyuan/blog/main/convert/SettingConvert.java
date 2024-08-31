package com.limyel.haoyuan.blog.main.convert;

import com.limyel.haoyuan.blog.main.entity.SettingEntity;
import com.limyel.haoyuan.blog.main.dto.setting.SettingDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SettingConvert {

    SettingConvert INSTANCE = Mappers.getMapper(SettingConvert.class);

    SettingEntity toDO(SettingDTO dto);

    SettingDTO toDTO(SettingEntity entity);


}