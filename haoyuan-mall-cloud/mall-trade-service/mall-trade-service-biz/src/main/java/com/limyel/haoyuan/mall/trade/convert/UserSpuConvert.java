package com.limyel.haoyuan.mall.trade.convert;

import com.limyel.haoyuan.mall.trade.entity.UserSpuEntity;
import com.limyel.haoyuan.mall.trade.vo.userspu.UserSpuVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserSpuConvert {

    UserSpuConvert INSTANCE = Mappers.getMapper(UserSpuConvert.class);

    UserSpuVO toVO(UserSpuEntity entity);


}