package com.limyel.haoyuan.module.system.auth.service;

import com.limyel.haoyuan.module.system.auth.dto.LoginDTO;
import com.limyel.haoyuan.module.system.auth.vo.LoginVO;

public interface AuthService {

    LoginVO login(LoginDTO dto);

}
