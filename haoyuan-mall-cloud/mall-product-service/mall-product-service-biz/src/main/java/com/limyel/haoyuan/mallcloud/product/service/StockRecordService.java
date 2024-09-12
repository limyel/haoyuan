package com.limyel.haoyuan.mallcloud.product.service;

import com.limyel.haoyuan.mallcloud.product.convert.StockRecordConvert;
import com.limyel.haoyuan.mallcloud.product.dao.StockRecordDao;
import com.limyel.haoyuan.mallcloud.product.dto.StockRecotdExist;
import com.limyel.haoyuan.mallcloud.product.entity.StockRecordEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockRecordService {

    private final StockRecordDao stockRecordDao;

    @Transactional(rollbackFor = Exception.class)
    public int create(Long skuId, Integer quantity, Integer skuNum, String orderSn) {
        StockRecordEntity stockRecord = new StockRecordEntity();
        stockRecord.setOrderSn(orderSn);
        stockRecord.setSkuId(skuId);
        stockRecord.setSkuNum(skuNum);
        stockRecord.setQuantity(quantity);
        return stockRecordDao.insert(stockRecord);
    }

    public List<StockRecotdExist> getByOrderSn(String orderSn) {
        List<StockRecordEntity> list = stockRecordDao.selectList(StockRecordEntity::getOrderSn, orderSn);

        return list.stream()
                .map(StockRecordConvert.INSTANCE::toExist)
                .toList();
    }

}
