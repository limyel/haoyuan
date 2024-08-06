package com.limyel.haoyuan.mall.member.api;

import com.limyel.haoyuan.common.cloud.config.FeignDecoderConfig;
import com.limyel.haoyuan.mall.member.dto.user.UserInfoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "mall-member", contextId = "user", configuration = FeignDecoderConfig.class)
public interface UserApi {

    @GetMapping("/member/app/user/get/by-username/{username}")
    UserInfoDTO getByUsername(@PathVariable("username") String username);

}
