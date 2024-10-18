package com.limyel.haoyuan.blog.main.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.limyel.haoyuan.blog.common.main.constant.MainRedisKey;
import com.limyel.haoyuan.blog.common.main.convert.SettingConvert;
import com.limyel.haoyuan.blog.common.main.dto.setting.SettingDTO;
import com.limyel.haoyuan.blog.common.main.entity.SettingEntity;
import com.limyel.haoyuan.blog.main.dao.SettingDao;
import com.limyel.haoyuan.common.core.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SettingService {

    private final SettingDao settingDao;

    private final StringRedisTemplate redisTemplate;

    public int update(SettingDTO dto) {
        SettingEntity setting = SettingConvert.INSTANCE.toDO(dto);
        int result = settingDao.insertOrUpdate(setting);
        if (result == 1) {
            SettingConvert.INSTANCE.toDTO(setting);
            cacheValue(dto.getLabel(), dto.getValue());
        }
        return result;
    }

    public SettingDTO getById(Long id) {
        SettingEntity result = settingDao.selectById(id);
        if (result == null) {
            throw new ServiceException("设置项不存在");
        }
        return SettingConvert.INSTANCE.toDTO(result);
    }

    public String getValue(String label, boolean secret) {
        Object o = redisTemplate.opsForHash().get(MainRedisKey.SETTING_KEY, label);
        if (o != null) {
            return String.valueOf(o);
        }

        SettingEntity setting = settingDao.selectOne(new LambdaQueryWrapper<SettingEntity>()
                .eq(SettingEntity::getLabel, label)
                .eq(SettingEntity::getSecret, secret));
        if (setting == null) {
            throw new ServiceException("设置项不存在");
        }
        cacheValue(label, setting.getValue());
        return setting.getValue();
    }

    private void cacheValue(String label, String value) {
        redisTemplate.opsForHash().put(MainRedisKey.SETTING_KEY, label, value);
    }

}
