package com.limyel.haoyuan.blog.content.service;

import com.limyel.haoyuan.blog.content.convert.SettingConvert;
import com.limyel.haoyuan.blog.content.dao.SettingDao;
import com.limyel.haoyuan.blog.content.domain.SettingEntity;
import com.limyel.haoyuan.blog.content.dto.setting.SettingDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SettingService {

    private final SettingDao settingDao;

    public int update(SettingDTO dto) {
        SettingEntity settingDO = SettingConvert.INSTANCE.toDO(dto);
        settingDO.setId(SettingEntity.DEFAULT_ID);
        return settingDao.insertOrUpdate(settingDO);
    }

    public SettingDTO get() {
        SettingEntity settingDO = settingDao.selectById(SettingEntity.DEFAULT_ID);
        return SettingConvert.INSTANCE.toDTO(settingDO);
    }


}
