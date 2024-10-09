package com.limyel.haoyuan.mallcloud.member.controller.rpc;

import com.limyel.haoyuan.cloud.member.dto.MemberUserSecurity;
import com.limyel.haoyuan.cloud.member.dto.PointBalanceChange;
import com.limyel.haoyuan.common.core.log.ApiOperationLog;
import com.limyel.haoyuan.common.core.pojo.R;
import com.limyel.haoyuan.mallcloud.member.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("rpcUserController")
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ApiOperationLog(description = "用户支付")
    @PostMapping("/point-balance/deduct")
    public R<Boolean> deductPointBalance(@Validated @RequestBody PointBalanceChange dto) {
        Integer result = userService.deductPointBalance(dto);
        return R.ok(result == 1);
    }

    @GetMapping("/get/by-username/{username}")
    public R<MemberUserSecurity> getByUsername(@PathVariable("username") String username) {
        MemberUserSecurity result = userService.getByUsername(username);
        return R.ok(result);
    }

    @GetMapping("/get/by-mobile/{mobile}")
    public R<MemberUserSecurity> getByMobile(@PathVariable("mobile") String mobile) {
        MemberUserSecurity result = userService.getByUsername(mobile);
        return R.ok(result);
    }

}
