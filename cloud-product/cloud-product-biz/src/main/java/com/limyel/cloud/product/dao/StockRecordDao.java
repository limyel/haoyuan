package com.limyel.cloud.product.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.limyel.cloud.product.model.entity.StockRecordEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StockRecordDao extends BaseMapper<StockRecordEntity> {
}
