package com.limyel.haoyuan.cloud.mall.trade.controller.app;

import com.limyel.haoyuan.cloud.mall.trade.dto.order.OrderConfirmDTO;
import com.limyel.haoyuan.cloud.mall.trade.dto.order.OrderPayDTO;
import com.limyel.haoyuan.cloud.mall.trade.dto.order.OrderSubmitDTO;
import com.limyel.haoyuan.cloud.mall.trade.service.OrderService;
import com.limyel.haoyuan.cloud.mall.trade.vo.order.OrderConfirmVO;
import com.limyel.haoyuan.cloud.mall.trade.vo.order.OrderListVO;
import com.limyel.haoyuan.common.core.log.ApiOperationLog;
import com.limyel.haoyuan.common.core.pojo.PageParam;
import com.limyel.haoyuan.common.core.pojo.R;
import com.limyel.haoyuan.common.mybatis.pojo.PageData;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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

    @ApiOperationLog(description = "确认订单")
    @PostMapping("/confirm")
    public R<OrderConfirmVO> confirm(@RequestBody OrderConfirmDTO dto) {
        OrderConfirmVO result = orderService.confirm(dto);
        return R.ok(result);
    }

    @ApiOperationLog(description = "提交订单")
    @PostMapping("/submit")
    public R<Map<String, String>> submit(@RequestBody OrderSubmitDTO dto) {
        String orderSn = orderService.submitOrderSn(dto);
//        String orderSn = orderService.submit(dto);
        Map<String, String> result = Map.of("orderSn", orderSn);
        return R.ok(result);
    }

    @ApiOperationLog(description = "支付订单")
    @PostMapping("/pay")
    public R<?> pay(@Validated @RequestBody OrderPayDTO dto) {
        orderService.pay(dto);
        return R.ok();
    }

    @PostMapping("/cancel")
    public void cancel(@Validated @RequestBody OrderPayDTO dto) {
        orderService.cancel(dto.getOrderSn());
    }

}
