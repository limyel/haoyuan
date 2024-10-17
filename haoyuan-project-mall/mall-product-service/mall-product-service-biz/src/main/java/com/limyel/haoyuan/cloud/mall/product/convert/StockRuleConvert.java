package com.limyel.haoyuan.cloud.mall.product.convert;

import com.limyel.haoyuan.cloud.mall.product.dto.stockrule.StockRuleDTO;
import com.limyel.haoyuan.cloud.mall.product.entity.StockRuleEntity;
import com.limyel.haoyuan.cloud.mall.product.vo.stockrule.StockRulePageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StockRuleConvert {

    StockRuleConvert INSTANCE = Mappers.getMapper(StockRuleConvert.class);

    StockRuleEntity toEntity(StockRuleDTO dto);

    StockRulePageVO toPageVO(StockRuleEntity entity);

    StockRuleDTO toDTO(StockRuleEntity entity);

}