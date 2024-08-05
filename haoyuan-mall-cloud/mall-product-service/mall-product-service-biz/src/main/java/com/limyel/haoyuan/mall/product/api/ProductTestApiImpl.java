package com.limyel.haoyuan.mall.product.api;

import com.limyel.haoyuan.mall.product.dto.TestDTO;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductTestApiImpl implements ProductTestApi {

    @Override
    public TestDTO test(Long id) {
        TestDTO testDTO = new TestDTO();
        testDTO.setName(id.toString());
        return testDTO;
    }

}
