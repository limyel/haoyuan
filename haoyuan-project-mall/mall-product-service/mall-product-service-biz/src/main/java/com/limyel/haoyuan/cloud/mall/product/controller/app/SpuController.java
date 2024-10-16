package com.limyel.haoyuan.cloud.mall.product.controller.app;

import com.limyel.haoyuan.cloud.mall.product.dto.spu.SpuListDTO;
import com.limyel.haoyuan.cloud.mall.product.service.SpuService;
import com.limyel.haoyuan.cloud.mall.product.vo.spu.SpuListVO;
import com.limyel.haoyuan.common.core.pojo.R;
import com.limyel.haoyuan.common.mybatis.pojo.PageData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spu")
@RequiredArgsConstructor
public class SpuController {

    private final SpuService spuService;

    @GetMapping("/get/list")
    public R<PageData<SpuListVO>> getList(SpuListDTO dto) {
        PageData<SpuListVO> result = spuService.getList(dto);
        return R.ok(result);
    }

}
