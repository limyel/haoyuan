package com.limyel.haoyuan.mall.member.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("adminPointLogController")
@RequestMapping("/point-log")
@RequiredArgsConstructor
public class PointLogController {

    @GetMapping("/get/page")
    public void getPage() {}

}
