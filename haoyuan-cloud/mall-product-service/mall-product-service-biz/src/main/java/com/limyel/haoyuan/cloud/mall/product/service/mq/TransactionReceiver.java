package com.limyel.haoyuan.cloud.mall.product.service.mq;

import com.limyel.haoyuan.cloud.mall.product.dto.StockRecotdExist;
import com.limyel.haoyuan.cloud.mall.product.dto.StockReturn;
import com.limyel.haoyuan.cloud.mall.product.service.SkuService;
import com.limyel.haoyuan.cloud.mall.product.service.StockRecordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RocketMQMessageListener(topic = "submit-order-tx", consumerGroup = "tx-consumer")
@RequiredArgsConstructor
public class TransactionReceiver implements RocketMQListener<String> {

    private final StockRecordService stockRecordService;

    private final SkuService skuService;

    @Override
    public void onMessage(String orderSn) {
        List<StockRecotdExist> stockRecords = stockRecordService.getByOrderSn(orderSn);
        if (stockRecords.isEmpty()) {
            return;
        }

        List<StockReturn.Sku> skus = new ArrayList<>();
        for (StockRecotdExist stockRecord : stockRecords) {
            StockReturn.Sku sku = new StockReturn.Sku();
            sku.setSkuId(stockRecord.getSkuId());
            sku.setQuantity(stockRecord.getQuantity());
            skus.add(sku);
        }
        StockReturn dto = new StockReturn();
        dto.setList(skus);
        skuService.returnStock(dto);
    }

}
