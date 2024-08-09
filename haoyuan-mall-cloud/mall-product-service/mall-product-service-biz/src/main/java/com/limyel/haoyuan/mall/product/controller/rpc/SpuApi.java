package com.limyel.haoyuan.mall.product.controller.rpc;

import com.limyel.haoyuan.common.core.pojo.R;
import com.limyel.haoyuan.mall.product.convert.SpuConvert;
import com.limyel.haoyuan.mall.product.dto.SpuRpcDTO;
import com.limyel.haoyuan.mall.product.dto.spu.SpuDTO;
import com.limyel.haoyuan.mall.product.service.SpuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spu")
@RequiredArgsConstructor
public class SpuApi {

    private final SpuService spuService;

    @GetMapping("/get/by-id/{id}")
    public R<SpuRpcDTO> getById(@PathVariable("id") Long id) {
        SpuDTO dto = spuService.getById(id);
        SpuRpcDTO result = SpuConvert.INSTANCE.toRpcDTO(dto);
        return R.ok(result);
    }

}
