package com.limyel.haoyuan.mall.member.controller.rpc;

import com.limyel.haoyuan.mall.common.member.convert.LevelConvert;
import com.limyel.haoyuan.mall.common.member.dto.level.LevelDTO;
import com.limyel.haoyuan.mall.common.member.dto.level.api.LevelInfo;
import com.limyel.haoyuan.mall.member.api.LevelApi;
import com.limyel.haoyuan.mall.member.service.LevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LevelApiImpl implements LevelApi {

    private final LevelService levelService;

    @Override
    public LevelInfo getById(Long id) {
        LevelDTO dto = levelService.getById(id);
        LevelInfo result = LevelConvert.INSTANCE.toInfo(dto);
        return result;
    }

}
