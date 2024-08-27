package com.limyel.haoyuan.mall.trade.convert;

import com.limyel.haoyuan.mall.trade.entity.UserProductEntity;
import com.limyel.haoyuan.mall.trade.vo.userspu.UserProductVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserSpuConvert {

    UserSpuConvert INSTANCE = Mappers.getMapper(UserSpuConvert.class);

    UserProductVO toVO(UserProductEntity entity);


}