package com.limyel.haoyuan.blog.admin.service;

import com.limyel.haoyuan.blog.admin.convert.UserConvert;
import com.limyel.haoyuan.blog.admin.dao.UserDao;
import com.limyel.haoyuan.blog.admin.domain.UserDO;
import com.limyel.haoyuan.blog.admin.dto.user.UpdatePasswordDTO;
import com.limyel.haoyuan.blog.admin.vo.UserInfoVO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    public UserInfoVO getCurrentUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        UserInfoVO result = new UserInfoVO();
        result.setUsername(username);
        return result;
    }

    public int updatePassword(UpdatePasswordDTO dto) {
        String password = passwordEncoder.encode(dto.getPassword());
        return userDao.updatePassword(dto.getUsername(), password);
    }

}
