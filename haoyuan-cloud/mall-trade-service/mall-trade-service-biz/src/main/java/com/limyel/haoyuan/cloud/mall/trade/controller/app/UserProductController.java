package com.limyel.haoyuan.cloud.mall.trade.controller.app;

import com.limyel.haoyuan.cloud.mall.trade.dto.userspu.UseProductDTO;
import com.limyel.haoyuan.cloud.mall.trade.service.UserProductService;
import com.limyel.haoyuan.cloud.mall.trade.vo.userspu.UserProductVO;
import com.limyel.haoyuan.cloud.security.util.SecurityUtil;
import com.limyel.haoyuan.common.core.pojo.R;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("@pms.memberAuthenticated()")
    @GetMapping("/get/me")
    public R<List<UserProductVO>> getMe() {
        List<UserProductVO> result = userProductService.getByUserId(SecurityUtil.getMemberUserId().get());
        return R.ok(result);
    }

    @PreAuthorize("@pms.memberAuthenticated()")
    @PostMapping("/use")
    public R<?> useSpu(@Validated @RequestBody UseProductDTO dto) {
        userProductService.useSpu(dto);
        return R.ok();
    }

}
