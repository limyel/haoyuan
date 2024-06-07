package com.limyel.haoyuan.admin.service;

import com.limyel.haoyuan.admin.dao.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;

}
