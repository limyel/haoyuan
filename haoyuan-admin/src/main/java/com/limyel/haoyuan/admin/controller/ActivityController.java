package com.limyel.haoyuan.admin.controller;

import com.limyel.haoyuan.common.api.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Haoyuan BannerController", description = "测试接口")
@RestController
@RequestMapping("/test")
public class ActivityController {

    @Operation(summary = "test", method = "GET")
    @GetMapping("/test")
    public R<String> test() {
        return R.success("hello");
    }

}
