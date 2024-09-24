package com.limyel.haoyuan.mallcloud.member.controller.rpc;

import com.limyel.haoyuan.common.core.pojo.R;
import com.limyel.haoyuan.mall.common.member.convert.LevelConvert;
import com.limyel.haoyuan.mall.common.member.dto.level.LevelDTO;
import com.limyel.haoyuan.mall.common.member.dto.level.api.LevelInfo;
import com.limyel.haoyuan.mallcloud.member.service.LevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("rpcLevelController")
@RequestMapping("/level")
@RequiredArgsConstructor
public class LevelController {

    private final LevelService levelService;

    @GetMapping("/get/by-id/{id}")
    public R<LevelInfo> getById(@PathVariable("id") Long id) {
        LevelDTO dto = levelService.getById(id);
        LevelInfo result = LevelConvert.INSTANCE.toInfo(dto);
        return R.ok(result);
    }

}
