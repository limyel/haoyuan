package com.limyel.haoyuan.blog.main.controller.blog;

import com.limyel.haoyuan.blog.main.dto.setting.SettingDTO;
import com.limyel.haoyuan.blog.main.service.SettingService;
import com.limyel.haoyuan.common.core.log.ApiOperationLog;
import com.limyel.haoyuan.common.core.pojo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController("blogSettingController")
@RequestMapping("/setting")
@RequiredArgsConstructor
@Api(tags = "设置模块")
public class SettingController {

    private final SettingService settingService;

    @GetMapping("/get/name")
    @ApiOperation("获取博客名")
    @ApiOperationLog(description = "获取博客名")
    public R<Map<String, String>> getName() {
        SettingDTO settingDTO = settingService.get();
        return R.ok(Map.of("name", settingDTO.getName()));
    }

    @GetMapping("/get/about")
    @ApiOperation("获取关于")
    @ApiOperationLog(description = "获取关于")
    public R<Map<String, String>> getAbout() {
        SettingDTO settingDTO = settingService.get();
        return R.ok(Map.of("about", settingDTO.getAbout()));
    }

}
