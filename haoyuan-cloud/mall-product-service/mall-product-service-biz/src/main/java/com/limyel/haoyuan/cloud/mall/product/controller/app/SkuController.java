package com.limyel.haoyuan.cloud.mall.product.controller.app;

import com.limyel.haoyuan.common.core.pojo.R;
import com.limyel.haoyuan.mall.common.product.vo.sku.SkuListVO;
import com.limyel.haoyuan.cloud.mall.product.service.SkuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sku")
@RequiredArgsConstructor
public class SkuController {

    private final SkuService skuService;

    @GetMapping("/get/by-spu/{spuId}")
    public R<List<SkuListVO>> getList(@PathVariable Long spuId) {
        List<SkuListVO> result = skuService.getBySpuId(spuId);
        return R.ok(result);
    }

}
