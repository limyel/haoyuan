package com.limyel.haoyuan.mall.trade.controller.app;

import com.limyel.haoyuan.common.core.pojo.PageParam;
import com.limyel.haoyuan.common.core.pojo.R;
import com.limyel.haoyuan.common.mybatis.pojo.PageData;
import com.limyel.haoyuan.mall.trade.dto.order.OrderConfirmDTO;
import com.limyel.haoyuan.mall.trade.service.OrderService;
import com.limyel.haoyuan.mall.trade.vo.order.OrderListVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/get/list")
    public R<PageData<OrderListVO>> getPage(PageParam pageParam) {
        PageData<OrderListVO> result = orderService.getList(pageParam);
        return R.ok(result);
    }

    @PostMapping("/confirm")
    public void confirm(@RequestBody OrderConfirmDTO dto) {

    }

    @PostMapping("/submit")
    public void submit() {

    }

    @PostMapping("/pay")
    public void pay() {

    }

    @PostMapping("/cancel")
    public void cancel() {

    }

}
