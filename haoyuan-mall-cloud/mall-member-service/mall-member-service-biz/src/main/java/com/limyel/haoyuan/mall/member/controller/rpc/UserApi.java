package com.limyel.haoyuan.mall.member.controller.rpc;

import com.limyel.haoyuan.common.core.pojo.R;
import com.limyel.haoyuan.mall.member.rdto.user.PointBalanceRDTO;
import com.limyel.haoyuan.mall.member.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserApi {

    private final UserService userService;

    @PostMapping("/point-balance/deduct")
    public R<Boolean> deductPointBalance(@Validated @RequestBody PointBalanceRDTO dto) {
        Integer result = userService.deductPointBalance(dto);
        return R.ok(result == 1);
    }

}
