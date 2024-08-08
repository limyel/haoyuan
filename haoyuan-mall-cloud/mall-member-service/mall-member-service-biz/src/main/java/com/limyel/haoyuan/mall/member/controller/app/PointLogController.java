package com.limyel.haoyuan.mall.member.controller.app;

import com.limyel.haoyuan.common.core.pojo.PageParam;
import com.limyel.haoyuan.common.core.pojo.R;
import com.limyel.haoyuan.common.mybatis.pojo.PageData;
import com.limyel.haoyuan.common.satoken.annotation.SaUserCheckLogin;
import com.limyel.haoyuan.common.satoken.service.StpUserUtil;
import com.limyel.haoyuan.mall.member.service.PointLogService;
import com.limyel.haoyuan.mall.member.vo.pointlog.PointLogListVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/point-log")
@RequiredArgsConstructor
public class PointLogController {

    private final PointLogService pointLogService;

    @SaUserCheckLogin
    @GetMapping("/get/list")
    public R<?> getList(PageParam pageParam) {
        Long loginId = (Long) StpUserUtil.getLoginId();
        PageData<PointLogListVO> result = pointLogService.getList(pageParam, loginId);
        return R.ok(result);
    }

}
