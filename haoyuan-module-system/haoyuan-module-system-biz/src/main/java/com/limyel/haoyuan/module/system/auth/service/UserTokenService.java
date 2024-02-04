package com.limyel.haoyuan.module.system.auth.service;

import com.limyel.haoyuan.module.system.auth.vo.TokenVO;

public interface UserTokenService {

    TokenVO generateToken(Long userId);

}
