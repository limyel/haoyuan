package com.limyel.haoyuan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.limyel.haoyuan.model.entity.Activity;
import com.limyel.haoyuan.model.vo.ActivityPureVO;

/**
 * <p>
 * 活动 服务类
 * </p>
 *
 * @author limyel
 * @since 2022-04-28
 */
public interface ActivityService extends IService<Activity> {

    ActivityPureVO getPureByName(String name);

}
