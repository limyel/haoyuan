package com.limyel.haoyuan.mall.trade.controller.app;

import com.limyel.haoyuan.common.core.pojo.R;
import com.limyel.haoyuan.common.satoken.annotation.SaUserCheckLogin;
import com.limyel.haoyuan.common.satoken.service.StpUserUtil;
import com.limyel.haoyuan.mall.trade.dto.userspu.UseSpuDTO;
import com.limyel.haoyuan.mall.trade.service.UserSpuService;
import com.limyel.haoyuan.mall.trade.vo.userspu.UserSpuVO;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user-spu")
@RequiredArgsConstructor
public class UserSpuController {

    private final UserSpuService userSpuService;

    @SaUserCheckLogin
    @GetMapping("/get/me")
    public R<List<UserSpuVO>> getMe() {
        List<UserSpuVO> result = userSpuService.getByUserId(StpUserUtil.getLoginIdAsLong());
        return R.ok(result);
    }

    @SaUserCheckLogin
    @PostMapping("/use")
    public R<?> useSpu(@Validated @RequestBody UseSpuDTO dto) {
        userSpuService.useSpu(dto);
        return R.ok();
    }

}
