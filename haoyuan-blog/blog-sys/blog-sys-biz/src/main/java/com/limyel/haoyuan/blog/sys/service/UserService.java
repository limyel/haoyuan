package com.limyel.haoyuan.blog.sys.service;

import com.limyel.haoyuan.blog.sys.convert.UserConvert;
import com.limyel.haoyuan.blog.sys.dao.UserDao;
import com.limyel.haoyuan.blog.sys.domain.UserEntity;
import com.limyel.haoyuan.blog.sys.dto.user.UpdatePasswordDTO;
import com.limyel.haoyuan.blog.sys.dto.user.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;

    private final PasswordEncoder passwordEncoder;

    @Transactional(rollbackFor = Exception.class)
    public int create(UserDTO dto) {
        userDao.validateUnique(null, UserEntity::getUsername, dto.getUsername(), "用户名已存在");

        UserEntity user = UserConvert.INSTANCE.toEntity(dto);
        int result = userDao.insert(user);

        return result;
    }

    public UserEntity getByUsername(String username) {
        return userDao.selectOne(UserEntity::getUsername, username);
    }

    public UserEntity getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        return getByUsername(username);
    }

    public int updatePassword(UpdatePasswordDTO dto) {
        String password = passwordEncoder.encode(dto.getPassword());
        return userDao.updatePassword(dto.getUsername(), password);
    }

}
