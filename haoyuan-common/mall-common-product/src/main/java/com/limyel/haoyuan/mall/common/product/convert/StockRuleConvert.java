package com.limyel.haoyuan.mall.common.product.convert;

import com.limyel.haoyuan.mall.common.product.dto.stockrule.StockRuleDTO;
import com.limyel.haoyuan.mall.common.product.entity.StockRuleEntity;
import com.limyel.haoyuan.mall.common.product.vo.stockrule.StockRulePageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StockRuleConvert {

    StockRuleConvert INSTANCE = Mappers.getMapper(StockRuleConvert.class);

    StockRuleEntity toEntity(StockRuleDTO dto);

    StockRulePageVO toPageVO(StockRuleEntity entity);

    StockRuleDTO toDTO(StockRuleEntity entity);

}