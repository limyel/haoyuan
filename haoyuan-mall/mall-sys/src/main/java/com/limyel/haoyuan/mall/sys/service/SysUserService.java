package com.limyel.haoyuan.mall.sys.service;

import com.limyel.haoyuan.mall.sys.dao.SysUserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SysUserService {

    private final SysUserDao sysUserDao;

}
