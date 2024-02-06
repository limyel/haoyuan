package com.limyel.haoyuan.module.system.auth.service;

import com.limyel.haoyuan.module.system.auth.vo.LoginVO;

public interface UserTokenService {

    LoginVO generateToken(Long userId);

}
