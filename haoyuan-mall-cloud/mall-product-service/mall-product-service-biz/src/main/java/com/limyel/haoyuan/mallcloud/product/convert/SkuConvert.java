package com.limyel.haoyuan.mallcloud.product.convert;

import com.limyel.haoyuan.mallcloud.product.dto.SkuConfirm;
import com.limyel.haoyuan.mallcloud.product.dto.sku.SkuDTO;
import com.limyel.haoyuan.mallcloud.product.entity.SkuEntity;
import com.limyel.haoyuan.mallcloud.product.vo.sku.SkuListVO;
import com.limyel.haoyuan.mallcloud.product.vo.sku.SkuPageVO;
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