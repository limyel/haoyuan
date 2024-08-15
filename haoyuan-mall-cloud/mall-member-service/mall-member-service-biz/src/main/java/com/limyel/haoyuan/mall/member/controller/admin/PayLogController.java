package com.limyel.haoyuan.mall.member.controller.admin;

import com.limyel.haoyuan.common.core.pojo.R;
import com.limyel.haoyuan.common.mybatis.pojo.PageData;
import com.limyel.haoyuan.mall.member.dto.pointlog.PayLogPageDTO;
import com.limyel.haoyuan.mall.member.service.PayLogService;
import com.limyel.haoyuan.mall.member.vo.pointlog.PayLogPageVO;
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
