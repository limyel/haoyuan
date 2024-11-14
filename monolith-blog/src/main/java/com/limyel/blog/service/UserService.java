package com.limyel.blog.service;

import com.limyel.blog.dao.UserReposiroty;
import com.limyel.blog.model.dto.LoginDTO;
import com.limyel.blog.model.entity.UserEntity;
import com.limyel.blog.util.CryptUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserReposiroty userReposiroty;

    public UserEntity doLogin(LoginDTO dto) {
        UserEntity user = userReposiroty.findByUsername(dto.getUsername());
        boolean match = CryptUtil.match(dto.getPassword(), user.getPassword());
        return match ? user : null;
    }
}
