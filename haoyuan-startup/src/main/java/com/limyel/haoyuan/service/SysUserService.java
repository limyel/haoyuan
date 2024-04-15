package com.limyel.haoyuan.service;

import com.limyel.haoyuan.dao.SysUserDao;
import com.limyel.haoyuan.entity.SysUserDO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SysUserService {

    private final SysUserDao sysUserDao;

    @Transactional
    public void create(Map<String, String> data) {
        SysUserDO sysUser = new SysUserDO();
        sysUser.setUsername(data.get("username"));
        sysUser.setPassword(data.get("password"));
        sysUserDao.insert(sysUser);
        try {
            throw new FileNotFoundException();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
