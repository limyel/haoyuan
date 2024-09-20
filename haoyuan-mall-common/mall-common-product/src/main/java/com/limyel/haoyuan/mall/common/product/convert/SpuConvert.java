package com.limyel.haoyuan.mall.common.product.convert;

import com.limyel.haoyuan.mall.common.product.dto.api.SkuConfirm;
import com.limyel.haoyuan.mall.common.product.dto.spu.SpuDTO;
import com.limyel.haoyuan.mall.common.product.entity.SpuEntity;
import com.limyel.haoyuan.mall.common.product.vo.spu.SpuListVO;
import com.limyel.haoyuan.mall.common.product.vo.spu.SpuPageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SpuConvert {

    SpuConvert INSTANCE = Mappers.getMapper(SpuConvert.class);

    SpuEntity toEntity(SpuDTO dto);

    SpuPageVO toPageVO(SpuEntity entity);

    SpuListVO toListVO(SpuEntity entity);

    SpuDTO toDTO(SpuEntity entity);

    SkuConfirm toRpcDTO(SpuDTO dto);

    SkuConfirm toRpcDTO(SpuEntity entity);

}