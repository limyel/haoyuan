package com.limyel.haoyuan.blog.main.service;

import com.limyel.haoyuan.blog.main.convert.SettingConvert;
import com.limyel.haoyuan.blog.main.dao.SettingDao;
import com.limyel.haoyuan.blog.main.entity.SettingEntity;
import com.limyel.haoyuan.blog.main.dto.setting.SettingDTO;
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
