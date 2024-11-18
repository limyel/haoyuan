package com.limyel.cloud.product.controller.admin;

import com.limyel.cloud.product.exception.ProductErrorCode;
import com.limyel.cloud.product.model.dto.product.ProductDTO;
import com.limyel.cloud.product.service.ProductService;
import com.limyel.haoyuan.common.core.pojo.R;
import com.limyel.haoyuan.common.core.util.AssertUtil;
import com.limyel.haoyuan.common.core.validator.group.Create;
import com.limyel.haoyuan.common.core.validator.group.Update;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/create")
    public R<?> create(@Validated(Create.class) @RequestBody ProductDTO dto) {
        int result = productService.create(dto);
        AssertUtil.assertTrue(result == 1, ProductErrorCode.PRODUCT_INSERT_FAILED);
        return R.ok();
    }

    @GetMapping("/delete")
    public R<?> delete(@RequestParam("ids") List<Long> ids) {
        productService.delete(ids);
        return R.ok();
    }

    @PostMapping("/update")
    public R<?> update(@Validated(Update.class) @RequestBody ProductDTO dto) {
        int result = productService.update(dto);
        AssertUtil.assertTrue(result == 1, ProductErrorCode.PRODUCT_UPDATE_FAILED);
        return R.ok();
    }



}
