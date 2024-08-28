package com.limyel.haoyuan.blog.main.service;

import com.limyel.haoyuan.blog.main.constant.MainRedisKey;
import com.limyel.haoyuan.blog.main.convert.SettingConvert;
import com.limyel.haoyuan.blog.main.dao.SettingDao;
import com.limyel.haoyuan.blog.main.dto.setting.SettingDTO;
import com.limyel.haoyuan.blog.main.entity.SettingEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SettingService {

    private final SettingDao settingDao;

    private final StringRedisTemplate redisTemplate;

    @CacheEvict(value = MainRedisKey.SETTING_KEY)
    public int update(SettingDTO dto) {
        SettingEntity setting = SettingConvert.INSTANCE.toDO(dto);
        setting.setId(SettingEntity.DEFAULT_ID);
        int result = settingDao.insertOrUpdate(setting);
        if (result == 1) {
            cacheDTO(setting);
        }
        return result;
    }

    @Cacheable(value = MainRedisKey.SETTING_KEY)
    public SettingDTO get() {
        SettingEntity setting = settingDao.selectById(SettingEntity.DEFAULT_ID);
        return cacheDTO(setting);
    }

    @CachePut(value = MainRedisKey.SETTING_KEY)
    public SettingDTO cacheDTO(SettingEntity entity) {
        return SettingConvert.INSTANCE.toDTO(entity);
    }
}
