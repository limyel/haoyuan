package com.limyel.haoyuan.member.controller.biz;

import com.limyel.haoyuan.common.core.pojo.PageParam;
import com.limyel.haoyuan.common.core.pojo.R;
import com.limyel.haoyuan.common.mybatis.pojo.PageData;
import com.limyel.haoyuan.member.service.PointLogService;
import com.limyel.haoyuan.member.vo.pointlog.PointLogListVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/point-log")
@RequiredArgsConstructor
public class PointLogController {

    private final PointLogService pointLogService;

    @GetMapping("/get/list")
    public R<PageData<PointLogListVO>> getList(PageParam pageParam) {
        PageData<PointLogListVO> result = pointLogService.getList(pageParam);
        return R.ok(result);
    }

}
