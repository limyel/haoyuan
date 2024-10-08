package com.limyel.haoyuan.mall.sys.api;

import com.limyel.haoyuan.common.cloud.config.FeignConfig;
import com.limyel.haoyuan.mall.common.auth.dto.SysUserSecurity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "haoyuan-sys", contextId = "sysUser", configuration = {FeignConfig.class})
public interface SysUserFeignClient {

    @GetMapping("/sys/rpc/sys-user/get/by-username/{username}")
    SysUserSecurity getByUsername(@PathVariable("username") String username);

}
