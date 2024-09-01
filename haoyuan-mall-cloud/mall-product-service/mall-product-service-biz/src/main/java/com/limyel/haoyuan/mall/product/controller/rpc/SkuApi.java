package com.limyel.haoyuan.mall.product.controller.rpc;

import com.limyel.haoyuan.common.core.log.ApiOperationLog;
import com.limyel.haoyuan.common.core.pojo.R;
import com.limyel.haoyuan.mall.product.dto.SkuConfirm;
import com.limyel.haoyuan.mall.product.dto.StockDeductRDTO;
import com.limyel.haoyuan.mall.product.service.SkuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sku")
@RequiredArgsConstructor
public class SkuApi {

    private final SkuService skuService;

    @GetMapping("/get/by-id/{id}")
    public R<SkuConfirm> getById(@PathVariable("id") Long id) {
        SkuConfirm result = skuService.getConfirm(id);
        return R.ok(result);
    }

    @GetMapping("/get/by-ids")
    public R<List<SkuConfirm>> getByIds(@RequestParam("ids") List<Long> ids) {
        List<SkuConfirm> result = skuService.getByIds(ids);
        return R.ok(result);
    }

    @ApiOperationLog(description = "扣减库存")
    @PostMapping("/deduct")
    public R<?> deductStock(@RequestBody StockDeductRDTO dto) {
        skuService.deductStock(dto);
        return R.ok();
    }

}
