package com.limyel.haoyuan.blog.main.convert;

import com.limyel.haoyuan.blog.main.domain.SettingDO;
import com.limyel.haoyuan.blog.main.dto.setting.SettingDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SettingConvert {

    SettingConvert INSTANCE = Mappers.getMapper(SettingConvert.class);

    SettingDO toDO(SettingDTO dto);

    SettingDTO toDTO(SettingDO settingDO);


}