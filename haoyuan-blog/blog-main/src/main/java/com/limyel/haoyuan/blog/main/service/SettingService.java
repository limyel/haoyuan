package com.limyel.haoyuan.blog.main.service;

import com.limyel.haoyuan.blog.main.convert.PostConvert;
import com.limyel.haoyuan.blog.main.convert.SettingConvert;
import com.limyel.haoyuan.blog.main.dao.PostDao;
import com.limyel.haoyuan.blog.main.dao.SettingDao;
import com.limyel.haoyuan.blog.main.domain.PostDO;
import com.limyel.haoyuan.blog.main.domain.SettingDO;
import com.limyel.haoyuan.blog.main.dto.post.PostDTO;
import com.limyel.haoyuan.blog.main.dto.setting.SettingDTO;
import com.limyel.haoyuan.blog.main.exception.MainErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SettingService {

    private final SettingDao settingDao;

    public int update(SettingDTO dto) {
        SettingDO settingDO = SettingConvert.INSTANCE.toDO(dto);
        settingDO.setId(SettingDO.DEFAULT_ID);
        return settingDao.insertOrUpdate(settingDO);
    }

    public SettingDTO get() {
        SettingDO settingDO = settingDao.selectById(SettingDO.DEFAULT_ID);
        return SettingConvert.INSTANCE.toDTO(settingDO);
    }


}
