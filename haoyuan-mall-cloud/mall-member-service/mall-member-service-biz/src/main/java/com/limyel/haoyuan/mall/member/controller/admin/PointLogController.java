package com.limyel.haoyuan.mall.member.controller.admin;

import com.limyel.haoyuan.common.core.pojo.R;
import com.limyel.haoyuan.common.mybatis.pojo.PageData;
import com.limyel.haoyuan.mall.member.dto.pointlog.PointLogPageDTO;
import com.limyel.haoyuan.mall.member.service.PointLogService;
import com.limyel.haoyuan.mall.member.vo.pointlog.PointLogPageVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("adminPointLogController")
@RequestMapping("/point-log")
@RequiredArgsConstructor
public class PointLogController {

    private final PointLogService pointLogService;

    @GetMapping("/get/page")
    public R<PageData<PointLogPageVO>> getPage(PointLogPageDTO dto) {
        PageData<PointLogPageVO> result = pointLogService.getPage(dto);
        return R.ok(result);
    }

}
