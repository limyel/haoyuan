package com.limyel.haoyuan.mall.sys.controller.rpc;

import com.limyel.haoyuan.common.core.pojo.R;
import com.limyel.haoyuan.mall.common.auth.dto.SysUserSecurity;
import com.limyel.haoyuan.mall.common.sys.convert.SysUserConvert;
import com.limyel.haoyuan.mall.common.sys.entity.SysUserEntity;
import com.limyel.haoyuan.mall.sys.service.MenuService;
import com.limyel.haoyuan.mall.sys.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController("rpcSysUserController")
@RequestMapping("/sys-user")
@RequiredArgsConstructor
public class SysUserController {

    private final SysUserService sysUserService;

    private final MenuService menuService;

    @GetMapping("/get/by-username/{username}")
    public R<SysUserSecurity> getByUsername(@PathVariable("username") String username) {
        SysUserEntity sysUser = sysUserService.getByUsername(username);

        SysUserSecurity result = SysUserConvert.INSTANCE.toSecurity(sysUser);
        Set<String> permissions = menuService.getPermissions(sysUser.getId());
        result.setPermissions(permissions);

        return R.ok(result);
    }

}
