package com.limyel.haoyuan.cloud.mall.product.convert;

import com.limyel.haoyuan.cloud.mall.product.dto.StockRecotdExist;
import com.limyel.haoyuan.cloud.mall.product.entity.StockRecordEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StockRecordConvert {

    StockRecordConvert INSTANCE = Mappers.getMapper(StockRecordConvert.class);

    StockRecotdExist toExist(StockRecordEntity entity);

}