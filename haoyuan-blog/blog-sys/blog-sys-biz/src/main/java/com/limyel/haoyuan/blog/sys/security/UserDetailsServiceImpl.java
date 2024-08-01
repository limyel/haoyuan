package com.limyel.haoyuan.blog.sys.security;

import com.limyel.haoyuan.blog.sys.constant.AdminErrorMsg;
import com.limyel.haoyuan.blog.sys.domain.UserEntity;
import com.limyel.haoyuan.blog.sys.domain.UserRoleEntity;
import com.limyel.haoyuan.blog.sys.service.UserRoleService;
import com.limyel.haoyuan.blog.sys.service.UserService;
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
            throw new ServiceException(AdminErrorMsg.USER_NOT_FOUND);
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
