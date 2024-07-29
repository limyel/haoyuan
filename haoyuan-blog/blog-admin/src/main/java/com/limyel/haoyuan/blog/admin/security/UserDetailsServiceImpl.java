package com.limyel.haoyuan.blog.admin.security;

import com.limyel.haoyuan.blog.admin.domain.UserEntity;
import com.limyel.haoyuan.blog.admin.domain.UserRoleEntity;
import com.limyel.haoyuan.blog.admin.exception.AdminErrorCode;
import com.limyel.haoyuan.blog.admin.service.UserRoleService;
import com.limyel.haoyuan.blog.admin.service.UserService;
import com.limyel.haoyuan.common.core.exception.ServiceException;
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
        UserEntity userEntity = userService.getByUsername(username);
        if (userEntity == null) {
            throw new ServiceException(AdminErrorCode.UserNotFound);
        }
        List<UserRoleEntity> userRoleEntities = userRoleService.getByUserId(userEntity.getId());
        String[] roles = new String[0];
        if (!userRoleEntities.isEmpty()) {
            roles = userRoleEntities.stream().map(UserRoleEntity::getRole).toList().toArray(new String[0]);
        }

        return User.withUsername(userEntity.getUsername())
                .password(userEntity.getPassword())
                .authorities(roles)
                .build();
    }
}
