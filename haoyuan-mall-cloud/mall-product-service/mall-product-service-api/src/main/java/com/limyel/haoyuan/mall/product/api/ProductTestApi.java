package com.limyel.haoyuan.mall.product.api;

import com.limyel.haoyuan.mall.product.dto.TestDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface ProductTestApi {

    @GetMapping("/{id}")
    TestDTO test(@PathVariable Long id);

}
