package com.limyel.haoyuan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.limyel.haoyuan.common.exception.ApiException;
import com.limyel.haoyuan.dao.ActivityMapper;
import com.limyel.haoyuan.model.entity.Activity;
import com.limyel.haoyuan.model.vo.ActivityPureVO;
import com.limyel.haoyuan.service.ActivityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * <p>
 * 活动 服务实现类
 * </p>
 *
 * @author limyel
 * @since 2022-04-28
 */
@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public ActivityPureVO getPureByName(String name) {
        LambdaQueryWrapper<Activity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Activity::getName, name);
        Optional<Activity> activityOptional = Optional.of(activityMapper.selectOne(queryWrapper));
        Activity activity = activityOptional.orElseThrow(() -> new ApiException(20002));
        return new ActivityPureVO(activity);
    }
}
