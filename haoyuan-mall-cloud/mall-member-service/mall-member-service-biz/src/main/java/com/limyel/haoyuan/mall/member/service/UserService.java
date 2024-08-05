package com.limyel.haoyuan.mall.member.service;

import com.limyel.haoyuan.mall.member.dao.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;



}
