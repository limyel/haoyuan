package com.limyel.haoyuan.blog.main.controller.admin;

import com.limyel.haoyuan.blog.common.main.vo.dashboard.StatisticsVO;
import com.limyel.haoyuan.blog.main.service.DashboardService;
import com.limyel.haoyuan.common.core.log.ApiOperationLog;
import com.limyel.haoyuan.common.core.pojo.R;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("adminDashboardController")
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/get/statistics")
    @ApiOperationLog(description = "获取统计数据")
    public R<StatisticsVO> getStatistics() {
        StatisticsVO result = dashboardService.getStatistics();
        return R.ok(result);
    }

    @GetMapping("/get/post-statistics")
    @ApiOperationLog(description = "获取统计数据")
    public R<StatisticsVO> getPostStatistics() {
        StatisticsVO result = dashboardService.getStatistics();
        return R.ok(result);
    }

}
