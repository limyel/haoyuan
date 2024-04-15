package com.limyel.haoyuan.controller;

import com.limyel.haoyuan.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sys/user")
@RequiredArgsConstructor
public class SysUserController {

    private final SysUserService sysUserService;

    private final List<HandlerMapping> handlerMapping;

    @PostMapping
    public Map create(@RequestBody Map<String, String> data) {
        try {
            sysUserService.create(data);
        } catch (Exception e) {
            e.printStackTrace();
        }

        HashMap<String, Object> result = new HashMap<>();
        return result;
    }

}
