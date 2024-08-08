package com.limyel.haoyuan.mall.sys.api;

import com.limyel.haoyuan.common.cloud.config.FeignDecoderConfig;
import com.limyel.haoyuan.mall.sys.dto.sysuser.SysUserInfoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "mall-sys", contextId = "sysUser", configuration = {FeignDecoderConfig.class})
public interface SysUserApi {

    @GetMapping("/sys/sys-user/get/by-username/{username}")
    SysUserInfoDTO getByUsername(@PathVariable("username") String username);

}
