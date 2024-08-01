package com.limyel.haoyuan.member.controller.sys;

import com.limyel.haoyuan.common.core.pojo.R;
import com.limyel.haoyuan.common.mybatis.pojo.PageData;
import com.limyel.haoyuan.member.dto.point.PointPageDTO;
import com.limyel.haoyuan.member.service.PointService;
import com.limyel.haoyuan.member.vo.point.PointPageVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("sysPointService")
@RequestMapping("/point")
@RequiredArgsConstructor
public class PointController {

    private final PointService pointService;

    @GetMapping("/get/page")
    public R<PageData<PointPageVO>> getPage(PointPageDTO dto) {
        PageData<PointPageVO> result = pointService.getPage(dto);
        return R.ok(result);
    }

}
