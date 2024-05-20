package com.limyel.haoyuan.system.controller;

import com.limyel.haoyuan.common.web.pojo.R;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys/captcha")
@RequiredArgsConstructor
public class CaptchaController {

    @GetMapping
    public R<?> get() {
        return null;
    }

}