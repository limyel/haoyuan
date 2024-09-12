package com.limyel.haoyuan.mallcloud.product.convert;

import com.limyel.haoyuan.mallcloud.product.dto.StockRecotdExist;
import com.limyel.haoyuan.mallcloud.product.entity.StockRecordEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StockRecordConvert {

    StockRecordConvert INSTANCE = Mappers.getMapper(StockRecordConvert.class);

    StockRecotdExist toExist(StockRecordEntity entity);

}