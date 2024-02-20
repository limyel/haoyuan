package com.limyel.haoyuan.module.system.security.filter;

import com.limyel.haoyuan.module.system.security.entity.LoginUser;
import com.limyel.haoyuan.module.system.security.service.UserTokenService;
import com.limyel.haoyuan.module.system.sys.entity.SysUserDO;
import com.limyel.haoyuan.module.system.sys.service.MenuService;
import com.limyel.haoyuan.module.system.sys.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserTokenService userTokenService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private UserRoleService userRoleService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 获取 token
        String token = request.getHeader("token");
        // 没有 token，放行
        if (!StringUtils.hasText(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        // 解析 token
        // todo 缓存
        SysUserDO sysUser = userTokenService.getUserByToken(token);
        Long userId = sysUser.getId();
        if (sysUser.getSuperAdmin()) {
            userId = null;
        }
        List<Long> roleIds = userRoleService.listRoleIdByUserId(userId);
        Set<String> permissions = new HashSet<>();
        if (!CollectionUtils.isEmpty(roleIds)) {
            permissions = menuService.listPermissionsByRoleIds(roleIds);
        }
        LoginUser loginUser = new LoginUser(sysUser, permissions);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request, response);
    }
}
