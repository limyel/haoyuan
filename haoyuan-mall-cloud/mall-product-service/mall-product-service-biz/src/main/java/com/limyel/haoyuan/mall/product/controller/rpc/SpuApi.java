package com.limyel.haoyuan.mall.product.controller.rpc;

import com.limyel.haoyuan.common.core.pojo.R;
import com.limyel.haoyuan.mall.product.convert.SpuConvert;
import com.limyel.haoyuan.mall.product.dto.SpuRDTO;
import com.limyel.haoyuan.mall.product.dto.StockDeductRDTO;
import com.limyel.haoyuan.mall.product.dto.spu.SpuDTO;
import com.limyel.haoyuan.mall.product.service.SpuService;
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
@RequestMapping("/spu")
@RequiredArgsConstructor
public class SpuApi {

    private final SpuService spuService;

    @GetMapping("/get/by-id/{id}")
    public R<SpuRDTO> getById(@PathVariable("id") Long id) {
        SpuDTO dto = spuService.getById(id);
        SpuRDTO result = SpuConvert.INSTANCE.toRpcDTO(dto);
        return R.ok(result);
    }

    @GetMapping("/get/by-ids")
    public R<List<SpuRDTO>> getByIds(@RequestParam("ids") List<Long> ids) {
        List<SpuRDTO> result = spuService.getByIds(ids);
        return R.ok(result);
    }

    @PostMapping("/deduct")
    public R<?> deductStock(@RequestBody StockDeductRDTO dto) {
        spuService.deductStock(dto);
        return R.ok();
    }

}
