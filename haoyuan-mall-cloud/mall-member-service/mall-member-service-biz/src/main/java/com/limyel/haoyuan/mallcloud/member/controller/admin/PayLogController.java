package com.limyel.haoyuan.mallcloud.member.controller.admin;

import com.limyel.haoyuan.common.core.pojo.R;
import com.limyel.haoyuan.common.mybatis.pojo.PageData;
import com.limyel.haoyuan.mall.common.member.dto.paylog.PayLogPageDTO;
import com.limyel.haoyuan.mall.common.member.vo.paylog.PayLogPageVO;
import com.limyel.haoyuan.mallcloud.member.service.PayLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("adminPointLogController")
@RequestMapping("/point-log")
@RequiredArgsConstructor
public class PayLogController {

    private final PayLogService payLogService;

    @GetMapping("/get/page")
    public R<PageData<PayLogPageVO>> getPage(PayLogPageDTO dto) {
        PageData<PayLogPageVO> result = payLogService.getPage(dto);
        return R.ok(result);
    }

}
