package com.limyel.haoyuan.mallcloud.product.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.limyel.haoyuan.common.core.exception.ServiceException;
import com.limyel.haoyuan.common.core.util.JSONUtil;
import com.limyel.haoyuan.common.mybatis.pojo.PageData;
import com.limyel.haoyuan.mall.member.api.LevelApi;
import com.limyel.haoyuan.mall.member.dto.level.LevelRDTO;
import com.limyel.haoyuan.mallcloud.product.constant.SpuRedisKey;
import com.limyel.haoyuan.mallcloud.product.convert.SkuConvert;
import com.limyel.haoyuan.mallcloud.product.dao.SkuDao;
import com.limyel.haoyuan.mallcloud.product.dto.SkuConfirm;
import com.limyel.haoyuan.mallcloud.product.dto.StockDeduct;
import com.limyel.haoyuan.mallcloud.product.dto.StockReturn;
import com.limyel.haoyuan.mallcloud.product.dto.sku.SkuDTO;
import com.limyel.haoyuan.mallcloud.product.dto.sku.SkuPageDTO;
import com.limyel.haoyuan.mallcloud.product.dto.spu.SpuDTO;
import com.limyel.haoyuan.mallcloud.product.entity.SkuEntity;
import com.limyel.haoyuan.mallcloud.product.vo.sku.SkuListVO;
import com.limyel.haoyuan.mallcloud.product.vo.sku.SkuPageVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
@Service
@RequiredArgsConstructor
public class SkuService {

    private final SkuDao skuDao;

    private final StringRedisTemplate redisTemplate;

    private final RedissonClient redissonClient;

    private final SpuService spuService;

    private final LevelApi levelApi;

    private final ThreadPoolExecutor threadPoolExecutor;

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
                    LevelRDTO level = levelApi.getById(sku.getMemberLevelId());
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
                    LevelRDTO level = levelApi.getById(sku.getMemberLevelId());
                    result.setMemberLevel(level.getName());
                    return result;
                })
                .toList();
    }

    /**
     * 扣减库存与归还库存使用同一把分布式锁！否则两个操作同时进行，会造成库存混乱。
     *
     * @param dto
     */
    public void deductStock(StockDeduct dto) {
        String orderToken = dto.getOrderToken();
        for (StockDeduct.SkuDTO skuDTO : dto.getSkuList()) {
            RLock lock = redissonClient.getLock("stock:lock:" + skuDTO.getSkuId());

            try {
                lock.lock();

                SkuEntity sku = skuDao.selectById(skuDTO.getSkuId());
                if (sku.getStock() < skuDTO.getQuantity()) {
                    throw new ServiceException("商品库存不足");
                }
                sku.setStock(sku.getStock() - skuDTO.getQuantity());
                int deductResult = skuDao.updateById(sku);

//            int deductResult = skuDao.update(new LambdaUpdateWrapper<SkuEntity>()
//                    .setSql("stock = stock - " + skuDTO.getQuantity())
//                    .eq(SkuEntity::getId, skuDTO.getSkuId())
//                    .apply("stock >= {0}", skuDTO.getQuantity())
//            );

                Assert.isTrue(deductResult == 1, "商品库存不足");

                redisTemplate.opsForList().rightPush(SpuRedisKey.SPU_STOCK_DEDUCT_PREFIX + orderToken, JSONUtil.toJson(skuDTO));
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (lock != null) {
                    lock.unlock();
                }
            }


        }
    }

    public void returnStock(StockReturn dto) {
        // todo 从 redis 获取
        for (StockReturn.Sku sku : dto.getList()) {
            threadPoolExecutor.execute(() -> {
                RLock lock = redissonClient.getLock("stock:lock:" + sku.getSkuId());

                try {
                    lock.lock();

                    SkuEntity skuEntity = skuDao.selectById(sku.getSkuId());
                    skuEntity.setStock(skuEntity.getStock() + sku.getQuantity());
                    skuDao.updateById(skuEntity);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (lock != null) {
                        lock.unlock();
                    }
                }
            });
        }
    }

}
