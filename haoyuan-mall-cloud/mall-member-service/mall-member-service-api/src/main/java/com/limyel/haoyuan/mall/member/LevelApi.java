package com.limyel.haoyuan.mall.member;

import com.limyel.haoyuan.common.cloud.config.FeignDecoderConfig;
import com.limyel.haoyuan.mall.member.rdto.level.LevelRDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "mall-member", contextId = "memberLevel", configuration = FeignDecoderConfig.class)
public interface LevelApi {

    @GetMapping("/member/rpc/level/get/by-id/{id}")
    LevelRDTO getById(@PathVariable("id") Long id);

}
