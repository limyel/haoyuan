package com.limyel.haoyuan.blog.content.convert;

import com.limyel.haoyuan.blog.content.domain.SettingEntity;
import com.limyel.haoyuan.blog.content.dto.setting.SettingDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SettingConvert {

    SettingConvert INSTANCE = Mappers.getMapper(SettingConvert.class);

    SettingEntity toDO(SettingDTO dto);

    SettingDTO toDTO(SettingEntity settingDO);


}