package com.limyel.haoyuan.mall.product.controller.app;

import com.limyel.haoyuan.common.core.pojo.PageParam;
import com.limyel.haoyuan.common.core.pojo.R;
import com.limyel.haoyuan.common.mybatis.pojo.PageData;
import com.limyel.haoyuan.common.satoken.annotation.SaUserCheckLogin;
import com.limyel.haoyuan.mall.product.dto.spu.SpuListDTO;
import com.limyel.haoyuan.mall.product.service.SpuService;
import com.limyel.haoyuan.mall.product.service.UserSpuService;
import com.limyel.haoyuan.mall.product.vo.spu.SpuListVO;
import com.limyel.haoyuan.mall.product.vo.spu.UserSpuVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spu")
@RequiredArgsConstructor
public class SpuController {

    private final SpuService spuService;

    private final UserSpuService userSpuService;

    @GetMapping("/get/list")
    public R<PageData<SpuListVO>> getList(SpuListDTO dto) {
        PageData<SpuListVO> result = spuService.getList(dto);
        return R.ok(result);
    }

    @SaUserCheckLogin
    @GetMapping("/get/me")
    public R<PageData<UserSpuVO>> getByCurrentUser(PageParam pageParam) {
        PageData<UserSpuVO> result = userSpuService.getList(pageParam);
        return R.ok(result);
    }

}
