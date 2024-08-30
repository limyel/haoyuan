package com.limyel.haoyuan.mall.member.api;

import com.limyel.haoyuan.common.cloud.config.FeignDecoderConfig;
import com.limyel.haoyuan.mall.member.dto.level.LevelRDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "mall-member", contextId = "memberLevel", path = "/member", configuration = FeignDecoderConfig.class)
public interface LevelApi {

    @GetMapping("/rpc/level/get/by-id/{id}")
    LevelRDTO getById(@PathVariable("id") Long id);

}
