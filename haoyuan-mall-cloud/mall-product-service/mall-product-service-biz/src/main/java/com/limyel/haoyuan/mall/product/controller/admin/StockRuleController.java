package com.limyel.haoyuan.mall.product.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("adminStockRuleController")
@RequestMapping("/stock-rule")
@RequiredArgsConstructor
public class StockRuleController {

    @PostMapping("/create")
    public void create() {

    }

    @GetMapping("/delete/by-ids/{ids}")
    public void delete(@PathVariable("ids") String ids) {

    }

    @PostMapping("/update")
    public void update() {

    }

    @GetMapping("/get/page")
    public void getPage() {

    }

    @GetMapping("/get/by-id/{id}")
    public void getById(@PathVariable Long id) {

    }

}
