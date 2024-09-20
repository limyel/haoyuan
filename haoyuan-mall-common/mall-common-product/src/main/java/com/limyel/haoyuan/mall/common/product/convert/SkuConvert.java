package com.limyel.haoyuan.mall.common.product.convert;

import com.limyel.haoyuan.mall.common.product.dto.api.SkuConfirm;
import com.limyel.haoyuan.mall.common.product.dto.sku.SkuDTO;
import com.limyel.haoyuan.mall.common.product.entity.SkuEntity;
import com.limyel.haoyuan.mall.common.product.vo.sku.SkuListVO;
import com.limyel.haoyuan.mall.common.product.vo.sku.SkuPageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SkuConvert {

    SkuConvert INSTANCE = Mappers.getMapper(SkuConvert.class);

    SkuEntity toEntity(SkuDTO dto);

    SkuPageVO toPageVO(SkuEntity entity);

    SkuDTO toDTO(SkuEntity entity);

    SkuConfirm toConfirm(SkuEntity entity);

    SkuConfirm toConfirm(SkuDTO dto);

    SkuListVO toListVO(SkuEntity entity);

}