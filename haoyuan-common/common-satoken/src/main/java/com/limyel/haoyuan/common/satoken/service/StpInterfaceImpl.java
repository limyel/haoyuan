package com.limyel.haoyuan.common.satoken.service;

import cn.dev33.satoken.stp.StpInterface;

import java.util.Collections;
import java.util.List;

public class StpInterfaceImpl implements StpInterface {

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        return Collections.emptyList();
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        return Collections.emptyList();
    }

}
