package com.limyel.haoyuan.blog.main.controller.admin;

import com.limyel.haoyuan.blog.common.main.vo.dashboard.StatisticsVO;
import com.limyel.haoyuan.blog.main.service.DashboardService;
import com.limyel.haoyuan.common.core.log.ApiOperationLog;
import com.limyel.haoyuan.common.core.pojo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("adminDashboardController")
@RequestMapping("/dashboard")
@Api(tags = "Admin 仪表盘模块")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/get/statistics")
    @ApiOperation("获取统计数据")
    @ApiOperationLog(description = "获取统计数据")
    public R<StatisticsVO> getStatistics() {
        StatisticsVO result = dashboardService.getStatistics();
        return R.ok(result);
    }

    @GetMapping("/get/post-statistics")
    @ApiOperation("获取统计数据")
    @ApiOperationLog(description = "获取统计数据")
    public R<StatisticsVO> getPostStatistics() {
        StatisticsVO result = dashboardService.getStatistics();
        return R.ok(result);
    }

}
