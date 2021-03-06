package com.limyel.haoyuan.service.impl;

import com.limyel.haoyuan.dao.UserMapper;
import com.limyel.haoyuan.model.entity.User;
import com.limyel.haoyuan.service.UserService;
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
