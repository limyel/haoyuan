package com.limyel.haoyuan.api;


import com.limyel.haoyuan.common.api.Response;
import com.limyel.haoyuan.service.ActivityService;
import com.limyel.haoyuan.vo.ActivityPureVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 活动 前端控制器
 * </p>
 *
 * @author limyel
 * @since 2022-04-28
 */
@RestController
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @GetMapping("/by-name/{name}")
    public Response<ActivityPureVO> getHome(
            @PathVariable String name
    ) {
        return Response.success(activityService.getPureByName(name));
    }

}

