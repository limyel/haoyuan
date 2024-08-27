package com.limyel.haoyuan.mall.member.controller.rpc;

import com.limyel.haoyuan.common.core.pojo.R;
import com.limyel.haoyuan.mall.member.convert.LevelConvert;
import com.limyel.haoyuan.mall.member.dto.level.LevelDTO;
import com.limyel.haoyuan.mall.member.rdto.level.LevelRDTO;
import com.limyel.haoyuan.mall.member.service.LevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/level")
@RequiredArgsConstructor
public class LevelApi {

    private final LevelService levelService;

    @GetMapping("/get/by-id/{id}")
    public R<LevelRDTO> getById(@PathVariable("id") Long id) {
        LevelDTO dto = levelService.getById(id);
        LevelRDTO result = LevelConvert.INSTANCE.toRDTO(dto);
        return R.ok(result);
    }

}
