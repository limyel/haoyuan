package com.limyel.haoyuan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.limyel.haoyuan.dao.ActivityCategoryMapper;
import com.limyel.haoyuan.entity.ActivityCategory;
import com.limyel.haoyuan.service.IActivityCategoryService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 活动分类关联表 服务实现类
 * </p>
 *
 * @author limyel
 * @since 2022-04-28
 */
@Service
public class ActivityCategoryServiceImpl extends ServiceImpl<ActivityCategoryMapper, ActivityCategory> implements IActivityCategoryService {

}
