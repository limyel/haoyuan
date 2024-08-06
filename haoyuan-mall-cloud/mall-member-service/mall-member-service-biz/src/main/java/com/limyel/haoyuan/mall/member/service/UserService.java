package com.limyel.haoyuan.mall.member.service;

import com.limyel.haoyuan.mall.member.convert.UserConvert;
import com.limyel.haoyuan.mall.member.dao.UserDao;
import com.limyel.haoyuan.mall.member.dto.user.UserInfoDTO;
import com.limyel.haoyuan.mall.member.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;

    public UserInfoDTO getByUsername(String username) {
        UserEntity user = userDao.selectOne(UserEntity::getUsername, username);
        return UserConvert.INSTANCE.toInfoDTO(user);
    }

}
