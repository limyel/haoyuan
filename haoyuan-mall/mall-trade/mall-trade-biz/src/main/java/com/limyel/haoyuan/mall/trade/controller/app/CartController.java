package com.limyel.haoyuan.mall.trade.controller.app;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    @GetMapping("/delete/by-ids/{ids}")
    public void deleteByIds(@PathVariable("ids") String ids) {

    }

    @PostMapping("/update")
    public void update() {

    }

    @GetMapping("/get")
    public void get() {

    }

    @GetMapping("/check")
    public void check() {

    }

    @GetMapping("/clear")
    public void clear() {

    }

    @PostMapping("/add")
    public void add() {

    }

}
