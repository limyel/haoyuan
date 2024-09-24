package com.limyel.haoyuan.mallcloud.member.controller.app;

import com.limyel.haoyuan.common.core.pojo.PageParam;
import com.limyel.haoyuan.common.core.pojo.R;
import com.limyel.haoyuan.common.mybatis.pojo.PageData;
import com.limyel.haoyuan.mall.common.member.vo.paylog.PayLogListVO;
import com.limyel.haoyuan.mallcloud.member.service.PayLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pay-log")
@RequiredArgsConstructor
public class PayLogController {

    private final PayLogService payLogService;

    @GetMapping("/get/list")
    public R<?> getList(PageParam pageParam) {
        Long loginId = null;
        PageData<PayLogListVO> result = payLogService.getList(pageParam, loginId);
        return R.ok(result);
    }

}
