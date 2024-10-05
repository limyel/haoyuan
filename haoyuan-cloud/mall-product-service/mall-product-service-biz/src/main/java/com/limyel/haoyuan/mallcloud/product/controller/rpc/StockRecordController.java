package com.limyel.haoyuan.mallcloud.product.controller.rpc;

import com.limyel.haoyuan.common.core.pojo.R;
import com.limyel.haoyuan.mall.common.product.dto.api.StockRecotdExist;
import com.limyel.haoyuan.mallcloud.product.service.StockRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("rpcStockRecordController")
@RequestMapping("/stock-record")
@RequiredArgsConstructor
public class StockRecordController {

    private final StockRecordService stockRecordService;

    @GetMapping("/exist/{orderSn}")
    public R<List<StockRecotdExist>> checkExist(@PathVariable("orderSn") String orderSn) {
        List<StockRecotdExist> result = stockRecordService.getByOrderSn(orderSn);
        return R.ok(result);
    }

}
