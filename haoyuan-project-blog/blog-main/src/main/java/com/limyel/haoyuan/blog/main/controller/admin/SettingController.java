package com.limyel.haoyuan.blog.main.controller.admin;

import com.limyel.haoyuan.blog.main.dto.setting.SettingDTO;
import com.limyel.haoyuan.blog.main.service.SettingService;
import com.limyel.haoyuan.common.core.log.ApiOperationLog;
import com.limyel.haoyuan.common.core.pojo.R;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("adminSettingController")
@RequestMapping("/setting")
@RequiredArgsConstructor
public class SettingController {

    private final SettingService settingService;

    @ApiOperationLog(description = "更新设置")
    @PostMapping("/update")
    public R<?> update(@Validated @RequestBody SettingDTO dto) {
        settingService.update(dto);
        // todo 异常处理
        return R.ok();
    }

    @ApiOperationLog(description = "获取设置")
    @GetMapping("/get/by-id/{id}")
    public R<SettingDTO> getById(@PathVariable("id") Long id) {
        SettingDTO result = settingService.getById(id);
        return R.ok(result);
    }

}
