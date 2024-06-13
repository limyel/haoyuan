package com.limyel.haoyuan.blog.admin.security;

import com.limyel.haoyuan.blog.admin.domain.UserDO;
import com.limyel.haoyuan.blog.admin.domain.UserRoleDO;
import com.limyel.haoyuan.blog.admin.exception.AdminErrorCode;
import com.limyel.haoyuan.blog.admin.service.UserRoleService;
import com.limyel.haoyuan.blog.admin.service.UserService;
import com.limyel.haoyuan.common.core.exception.BizException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    private final UserRoleService userRoleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDO userDO = userService.getByUsername(username);
        if (userDO == null) {
            throw new BizException(AdminErrorCode.UserNotFound);
        }
        List<UserRoleDO> userRoleDOs = userRoleService.getByUserId(userDO.getId());
        String[] roles = new String[0];
        if (!userRoleDOs.isEmpty()) {
            roles = userRoleDOs.stream().map(UserRoleDO::getRole).toList().toArray(new String[0]);
        }

        return User.withUsername(userDO.getUsername())
                .password(userDO.getPassword())
                .authorities(roles)
                .build();
    }
}
