package com.limyel.haoyuan.mall.member.api;

import com.limyel.haoyuan.common.cloud.config.FeignConfig;
import com.limyel.haoyuan.mall.common.member.dto.level.api.LevelInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "mall-member", contextId = "memberLevel", path = "/member", configuration = FeignConfig.class)
public interface LevelApi {

    @GetMapping("/rpc/level/get/by-id/{id}")
    LevelInfo getById(@PathVariable("id") Long id);

}
