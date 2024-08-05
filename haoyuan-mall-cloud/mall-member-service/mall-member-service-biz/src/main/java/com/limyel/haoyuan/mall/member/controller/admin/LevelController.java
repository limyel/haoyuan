package com.limyel.haoyuan.mall.member.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("adminLevelControler")
@RequestMapping("/level")
@RequiredArgsConstructor
public class LevelController {

    @PostMapping("/create")
    public void create() {

    }

    @GetMapping("/delete/{ids}")
    public void delete(@PathVariable("ids") String ids) {

    }

    @PostMapping("/update")
    public void update() {

    }

    @GetMapping("/get/page")
    public void getPage() {

    }

    @GetMapping("/get/by-id/{id}")
    public void get(@PathVariable("id") Long id) {

    }

}
