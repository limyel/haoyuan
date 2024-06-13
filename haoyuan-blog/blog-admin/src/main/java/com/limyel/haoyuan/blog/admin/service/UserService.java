package com.limyel.haoyuan.blog.admin.service;

import com.limyel.haoyuan.blog.admin.dao.UserDao;
import com.limyel.haoyuan.blog.admin.domain.UserDO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;

    public UserDO getByUsername(String username) {
        return userDao.selectOne(UserDO::getUsername, username);
    }

}
