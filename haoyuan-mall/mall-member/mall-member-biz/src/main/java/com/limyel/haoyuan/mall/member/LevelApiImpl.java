package com.limyel.haoyuan.mall.member;

import com.limyel.haoyuan.mall.member.api.LevelApi;
import com.limyel.haoyuan.mall.member.dto.level.LevelInfo;
import com.limyel.haoyuan.mall.member.convert.LevelConvert;
import com.limyel.haoyuan.mall.member.entity.LevelEntity;
import com.limyel.haoyuan.mall.member.service.LevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LevelApiImpl implements LevelApi {

    private final LevelService levelService;

    @Override
    public LevelInfo getById(Long id) {
        LevelEntity level = levelService.getById(id);
        return LevelConvert.INSTANCE.toInfo(level);
    }

}
