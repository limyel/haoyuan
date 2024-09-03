package com.limyel.haoyuan.mall.product.convert;

import com.limyel.haoyuan.mall.product.vo.stockrule.StockRulePageVO;
import com.limyel.haoyuan.mall.product.dto.stockrule.StockRuleDTO;
import com.limyel.haoyuan.mall.product.entity.StockRuleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StockRuleConvert {

    StockRuleConvert INSTANCE = Mappers.getMapper(StockRuleConvert.class);

    StockRuleEntity toEntity(StockRuleDTO dto);

    StockRulePageVO toPageVO(StockRuleEntity entity);

    StockRuleDTO toDTO(StockRuleEntity entity);

}