package com.limyel.haoyuan.mall.auth.service;

import com.limyel.haoyuan.common.core.exception.ServiceException;
import com.limyel.haoyuan.common.core.util.SpringContextUtil;
import com.limyel.haoyuan.mall.auth.vo.LoginVO;

public interface AuthStrategy {

    String BASE_NAME = "AuthStrategy";

    static LoginVO login(String authInfo, String loginType, String authType) {
        String beanName = authType + BASE_NAME;
        if (!SpringContextUtil.containsBean(beanName)) {
            throw new ServiceException("认证类型不正确");
        }
        AuthStrategy instance = SpringContextUtil.getBean(beanName);
        return instance.login(authInfo, loginType);
    }

    LoginVO login(String authInfo, String loginType);

}
