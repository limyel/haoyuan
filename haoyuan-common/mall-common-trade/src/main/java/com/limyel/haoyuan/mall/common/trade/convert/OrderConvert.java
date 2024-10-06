package com.limyel.haoyuan.mall.common.trade.convert;

import com.limyel.haoyuan.mall.common.trade.entity.OrderEntity;
import com.limyel.haoyuan.mall.common.trade.vo.order.OrderListVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderConvert {

    OrderConvert INSTANCE = Mappers.getMapper(OrderConvert.class);

    OrderListVO toListVO(OrderEntity entity);


}