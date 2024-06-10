package com.limyel.haoyuan.blog.admin.service;

import com.limyel.haoyuan.blog.admin.dao.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;

}
