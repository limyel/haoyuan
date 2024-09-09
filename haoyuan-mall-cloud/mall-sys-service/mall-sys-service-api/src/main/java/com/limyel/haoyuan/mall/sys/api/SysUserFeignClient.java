package com.limyel.haoyuan.mall.sys.api;

import com.limyel.haoyuan.common.cloud.config.FeignDecoderConfig;
import com.limyel.haoyuan.mall.sys.dto.sysuser.SysUserSecurity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "mall-sys", contextId = "sysUser", configuration = {FeignDecoderConfig.class})
public interface SysUserFeignClient {

    @GetMapping("/sys/rpc/sys-user/get/by-username/{username}")
    SysUserSecurity getByUsername(@PathVariable("username") String username);

}
