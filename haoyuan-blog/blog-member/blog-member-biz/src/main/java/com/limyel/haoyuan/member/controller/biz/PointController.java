package com.limyel.haoyuan.member.controller.biz;

import com.limyel.haoyuan.common.core.pojo.R;
import com.limyel.haoyuan.member.service.PointService;
import com.limyel.haoyuan.member.vo.point.PointInfoVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/point")
@RequiredArgsConstructor
public class PointController {

    private final PointService pointService;

    @GetMapping("/get/info")
    public R<PointInfoVO> getInfo() {
        PointInfoVO result = pointService.getByCurrentUser();
        return R.ok(result);
    }

}
