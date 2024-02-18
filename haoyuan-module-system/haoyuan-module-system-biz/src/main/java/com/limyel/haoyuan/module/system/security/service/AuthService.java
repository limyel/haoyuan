package com.limyel.haoyuan.module.system.security.service;

import com.limyel.haoyuan.module.system.security.dto.LoginDTO;
import com.limyel.haoyuan.module.system.security.vo.LoginVO;

public interface AuthService {

    LoginVO login(LoginDTO dto);

    void logout(Long userId);

}
