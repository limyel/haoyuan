package com.limyel.haoyuan.blog.admin.service;

import com.limyel.haoyuan.blog.admin.convert.UserConvert;
import com.limyel.haoyuan.blog.admin.dao.UserDao;
import com.limyel.haoyuan.blog.admin.domain.UserDO;
import com.limyel.haoyuan.blog.admin.dto.UpdatePasswordDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;

    private final PasswordEncoder passwordEncoder;

    public UserDO getByUsername(String username) {
        return userDao.selectOne(UserDO::getUsername, username);
    }

    public int updatePassword(UpdatePasswordDTO dto) {
        UserDO userDO = UserConvert.INSTANCE.toDO(dto);
        userDO.setPassword(passwordEncoder.encode(userDO.getPassword()));
        return userDao.updateById(userDO);
    }

}
