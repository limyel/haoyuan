package com.limyel.haoyuan.mall.common.product.convert;

import com.limyel.haoyuan.mall.common.product.dto.api.StockRecotdExist;
import com.limyel.haoyuan.mall.common.product.entity.StockRecordEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StockRecordConvert {

    StockRecordConvert INSTANCE = Mappers.getMapper(StockRecordConvert.class);

    StockRecotdExist toExist(StockRecordEntity entity);

}