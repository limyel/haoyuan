package com.limyel.haoyuan.blog.main.controller.app;

import com.limyel.haoyuan.blog.main.constant.SettingLabelEnum;
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
        String result = settingService.getValue(SettingLabelEnum.BLOG_NAME.getLabel(), false);
        return R.ok(Map.of("name", result));
    }

    @GetMapping("/get/about")
    @ApiOperation("获取关于")
    @ApiOperationLog(description = "获取关于")
    public R<Map<String, String>> getAbout() {
        String result = settingService.getValue(SettingLabelEnum.BLOG_ABOUT.getLabel(), false);
        return R.ok(Map.of("about", result));
    }

}
