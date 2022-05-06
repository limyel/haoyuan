package com.limyel.haoyuan.portal.service.impl;

import com.limyel.haoyuan.portal.dao.UserMapper;
import com.limyel.haoyuan.portal.entity.User;
import com.limyel.haoyuan.portal.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author limyel
 * @since 2022-04-28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
