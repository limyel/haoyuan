package com.limyel.haoyuan.mall.trade.controller.app;

import com.limyel.haoyuan.common.core.pojo.R;
import com.limyel.haoyuan.mall.common.trade.dto.userspu.UseProductDTO;
import com.limyel.haoyuan.mall.common.trade.vo.userspu.UserProductVO;
import com.limyel.haoyuan.mall.trade.service.UserProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user-product")
@RequiredArgsConstructor
public class UserProductController {

    private final UserProductService userProductService;

    @GetMapping("/get/me")
    public R<List<UserProductVO>> getMe() {
        List<UserProductVO> result = userProductService.getList();
        return R.ok(result);
    }

    @PostMapping("/use")
    public R<?> useSpu(@Validated @RequestBody UseProductDTO dto) {
        userProductService.useProduct(dto);
        return R.ok();
    }

}
