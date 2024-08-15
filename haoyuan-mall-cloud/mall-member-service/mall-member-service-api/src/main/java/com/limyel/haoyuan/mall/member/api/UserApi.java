package com.limyel.haoyuan.mall.member.api;

import com.limyel.haoyuan.common.cloud.config.FeignDecoderConfig;
import com.limyel.haoyuan.mall.member.rdto.user.PointBalanceRDTO;
import com.limyel.haoyuan.mall.member.rdto.user.UserInfoRDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "mall-member", contextId = "user", configuration = FeignDecoderConfig.class)
public interface UserApi {

    @GetMapping("/member/app/user/get/by-username/{username}")
    UserInfoRDTO getByUsername(@PathVariable("username") String username);

    @PostMapping("/member/rpc/user/point-balance/deduct")
    Boolean deductPointBalance(@Validated @RequestBody PointBalanceRDTO dto);

}
