package com.limyel.haoyuan.blog.main.controller.sys;

import com.limyel.haoyuan.blog.main.dto.setting.SettingDTO;
import com.limyel.haoyuan.blog.main.service.SettingService;
import com.limyel.haoyuan.common.core.log.ApiOperationLog;
import com.limyel.haoyuan.common.core.pojo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("adminSettingController")
@RequestMapping("/setting")
@Api(tags = "Admin 设置模块")
@RequiredArgsConstructor
public class SettingController {

    private final SettingService settingService;

    @ApiOperation("更新设置")
    @ApiOperationLog(description = "更新设置")
    @PostMapping("/update")
    public R<?> update(@Validated @RequestBody SettingDTO dto) {
        settingService.update(dto);
        // todo 异常处理
        return R.ok();
    }

    @ApiOperation("获取设置")
    @ApiOperationLog(description = "获取设置")
    @GetMapping("/get")
    public R<SettingDTO> getById() {
        SettingDTO result = settingService.get();
        return R.ok(result);
    }

}
