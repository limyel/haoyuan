package com.limyel.haoyuan.api.controller;


import com.limyel.haoyuan.common.api.R;
import com.limyel.haoyuan.model.vo.ActivityPureVO;
import com.limyel.haoyuan.service.ActivityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Activity", description = "活动接口")
@RestController
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @Operation(summary = "根据名称获取活动", method = "GET")
    @GetMapping("/by-name/{name}")
    public R<ActivityPureVO> getByName(
            @PathVariable String name
    ) {
        return R.success(activityService.getPureByName(name));
    }

}

