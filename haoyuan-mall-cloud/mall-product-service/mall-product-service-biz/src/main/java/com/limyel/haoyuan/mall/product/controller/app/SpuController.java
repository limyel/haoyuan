package com.limyel.haoyuan.mall.product.controller.app;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spu")
@RequiredArgsConstructor
public class SpuController {

    @GetMapping("/get/page")
    public void getPage() {

    }

    @GetMapping("/get/me")
    public void getByCurrentUser() {

    }

}
