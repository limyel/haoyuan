package com.limyel.haoyuan.mall.trade.controller.app;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    @GetMapping("/get/page")
    public void getPage() {

    }

    @PostMapping("/confirm")
    public void confirm() {

    }

    @PostMapping("/submit")
    public void submit() {

    }

    @PostMapping("/pay")
    public void pay() {

    }

    @PostMapping("/cancel")
    public void cancel() {

    }

}
