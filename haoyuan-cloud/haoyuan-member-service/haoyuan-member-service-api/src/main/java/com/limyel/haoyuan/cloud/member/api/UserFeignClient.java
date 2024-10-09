package com.limyel.haoyuan.cloud.member.api;

import com.limyel.haoyuan.cloud.member.dto.MemberUserSecurity;
import com.limyel.haoyuan.cloud.member.dto.PointBalanceChange;
import com.limyel.haoyuan.common.cloud.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "haoyuan-member", contextId = "user", path = "/member", configuration = FeignConfig.class)
public interface UserFeignClient {

    @GetMapping("/rpc/user/get/by-username/{username}")
    MemberUserSecurity getByUsername(@PathVariable("username") String username);

    @GetMapping("/rpc/user/get/by-mobille/{mobile}")
    MemberUserSecurity getByMobile(@PathVariable("mobile") String mobile);

    @PostMapping("/rpc/user/point-balance/deduct")
    Boolean deductPointBalance(@Validated @RequestBody PointBalanceChange dto);

}
