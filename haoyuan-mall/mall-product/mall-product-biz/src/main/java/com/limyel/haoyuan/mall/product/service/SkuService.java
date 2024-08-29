package com.limyel.haoyuan.mall.product.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.limyel.haoyuan.common.core.exception.ServiceException;
import com.limyel.haoyuan.common.core.util.JSONUtil;
import com.limyel.haoyuan.common.mybatis.pojo.PageData;
import com.limyel.haoyuan.mall.member.api.LevelApi;
import com.limyel.haoyuan.mall.member.dto.level.LevelInfo;
import com.limyel.haoyuan.mall.product.dto.SkuConfirm;
import com.limyel.haoyuan.mall.product.dto.StockDeduct;
import com.limyel.haoyuan.mall.product.constant.SpuRedisKey;
import com.limyel.haoyuan.mall.product.convert.SkuConvert;
import com.limyel.haoyuan.mall.product.dao.SkuDao;
import com.limyel.haoyuan.mall.product.dto.sku.SkuDTO;
import com.limyel.haoyuan.mall.product.dto.sku.SkuPageDTO;
import com.limyel.haoyuan.mall.product.dto.spu.SpuDTO;
import com.limyel.haoyuan.mall.product.entity.SkuEntity;
import com.limyel.haoyuan.mall.product.vo.sku.SkuListVO;
import com.limyel.haoyuan.mall.product.vo.sku.SkuPageVO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SkuService {

    private final SkuDao skuDao;

    private final StringRedisTemplate redisTemplate;

    private final SpuService spuService;

    private final LevelApi levelApi;

    public int create(SkuDTO dto) {
        skuDao.validateUnique(null, SkuEntity::getName, dto.getName(), "SKU 名称已存在");

        SkuEntity sku = SkuConvert.INSTANCE.toEntity(dto);
        return skuDao.insert(sku);
    }

    public int delete(List<Long> ids) {
        return skuDao.deleteBatchIds(ids);
    }

    public int update(SkuDTO dto) {
        skuDao.validateUnique(dto.getId(), SkuEntity::getName, dto.getName(), "SKU 名称已存在");

        SkuEntity sku = SkuConvert.INSTANCE.toEntity(dto);
        return skuDao.updateById(sku);
    }

    public PageData<SkuPageVO> getPage(SkuPageDTO dto) {
        Page<SkuEntity> page = new Page<>(dto.getPageNum(), dto.getPageSize());

        LambdaQueryWrapper<SkuEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.hasText(dto.getName()), SkuEntity::getName, dto.getName());
        wrapper.eq(dto.getSpuId() != null, SkuEntity::getSpuId, dto.getSpuId());
        wrapper.eq(dto.getMemberLevelId() != null, SkuEntity::getMemberLevelId, dto.getMemberLevelId());
        skuDao.selectPage(page, wrapper);

        List<SkuPageVO> list = page.getRecords().stream()
                .map(sku -> {
                    SkuPageVO result = SkuConvert.INSTANCE.toPageVO(sku);
                    result.setSpuName(spuService.getById(sku.getSpuId()).getName());
                    LevelInfo level = levelApi.getById(sku.getMemberLevelId());
                    result.setMemberLevel(level.getName());
                    return result;
                })
                .toList();
        return new PageData<>(page, list);
    }

    public SkuDTO getById(Long id) {
        SkuEntity sku = skuDao.selectById(id);
        if (sku == null) {
            throw new ServiceException("SKU 不存在");
        }
        return SkuConvert.INSTANCE.toDTO(sku);
    }

    public SkuConfirm getConfirm(Long id) {
        SkuDTO sku = getById(id);
        SkuConfirm result = SkuConvert.INSTANCE.toConfirm(sku);
        SpuDTO spu = spuService.getById(sku.getSpuId());
        result.setSpuName(spu.getName());
        result.setType(spu.getType());
        return result;
    }

    public List<SkuConfirm> getByIds(List<Long> ids) {
        LambdaQueryWrapper<SkuEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(SkuEntity::getId, ids);
        List<SkuEntity> skuList = skuDao.selectList(wrapper);

        return skuList.stream()
                .map(SkuConvert.INSTANCE::toConfirm)
                .toList();
    }

    public List<SkuListVO> getBySpuId(Long spuId) {
        List<SkuEntity> skuList = skuDao.selectList(SkuEntity::getSpuId, spuId);
        return skuList.stream()
                .map(sku -> {
                    SkuListVO result = SkuConvert.INSTANCE.toListVO(sku);
                    LevelInfo level = levelApi.getById(sku.getMemberLevelId());
                    result.setMemberLevel(level.getName());
                    return result;
                })
                .toList();
    }

    /**
     * todo 分布式锁
     * 互斥性、可重入性、锁超时，防死锁、锁释放正确，防误删、阻塞和非阻塞、公平和非公平
     * 扣减库存
     * @param dto
     */
    public void deductStock(StockDeduct dto) {
        String orderToken = dto.getOrderToken();
        for (StockDeduct.SkuDTO skuDTO : dto.getSkuList()) {
//            SpuEntity spu = spuDao.selectById(spuDTO.getSpuId());
//            if (spu.getStock() < spuDTO.getQuantity()) {
//                throw new ServiceException("商品库存不足");
//            }
//            spu.setStock(spu.getStock() - spuDTO.getQuantity());
//            spuDao.updateById(spu);

            // 使用 mysql 悲观锁的问题：
            // 1、易造成锁范围过大
            // 2、无法在程序中获取扣减库存之前的库存值
            // 3、很多场景下无法满足业务诉求
            int deductResult = skuDao.update(new LambdaUpdateWrapper<SkuEntity>()
                    .setSql("stock = stock - " + skuDTO.getQuantity())
                    .eq(SkuEntity::getId, skuDTO.getSkuId())
                    .apply("stock >= {0}", skuDTO.getQuantity())
            );
            Assert.isTrue(deductResult == 1, "商品库存不足");

            redisTemplate.opsForList().rightPush(SpuRedisKey.SPU_STOCK_DEDUCT_PREFIX + orderToken, JSONUtil.toJson(skuDTO));
        }
    }

}
