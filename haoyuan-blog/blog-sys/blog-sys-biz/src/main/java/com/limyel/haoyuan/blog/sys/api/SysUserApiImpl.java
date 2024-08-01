package com.limyel.haoyuan.blog.sys.api;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.limyel.haoyuan.blog.sys.dao.UserDao;
import com.limyel.haoyuan.blog.sys.domain.UserEntity;
import com.limyel.haoyuan.blog.sys.service.UserService;
import com.limyel.haoyuan.blog.sys.vo.user.CurrentUserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SysUserApiImpl implements SysUserApi {

    private final UserDao userDao;

    private final UserService userService;

    @Override
    public CurrentUserVO getCurrentUser() {
        UserEntity currentUser = userService.getCurrentUser();
        CurrentUserVO result = new CurrentUserVO();
        result.setId(currentUser.getId());
        result.setUsername(currentUser.getUsername());
        return result;
    }

    @Override
    public Map<Long, String> getIdUsernameMap(String username) {
        HashMap<Long, String> result = new HashMap<>();

        LambdaQueryWrapper<UserEntity> wrapperPlus = new LambdaQueryWrapper<>();
        wrapperPlus.like(UserEntity::getUsername, username);
        List<UserEntity> users = userDao.selectList(wrapperPlus);

        for (UserEntity user : users) {
            result.put(user.getId(), user.getUsername());
        }
        return result;
    }

}
