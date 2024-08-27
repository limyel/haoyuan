package com.limyel.haoyuan.mall.member.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.limyel.haoyuan.common.core.exception.ServiceException;
import com.limyel.haoyuan.common.mybatis.pojo.PageData;
import com.limyel.haoyuan.common.mybatis.query.LambdaQueryWrapperPlus;
import com.limyel.haoyuan.mall.member.convert.LevelConvert;
import com.limyel.haoyuan.mall.member.dao.LevelDao;
import com.limyel.haoyuan.mall.member.dto.level.LevelDTO;
import com.limyel.haoyuan.mall.member.dto.level.LevelPageDTO;
import com.limyel.haoyuan.mall.member.entity.LevelEntity;
import com.limyel.haoyuan.mall.member.vo.level.LevelPageVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LevelService {

    private final LevelDao levelDao;

    public int create(LevelDTO dto) {
        levelDao.validateUnique(null, LevelEntity::getName, dto.getName(), "等级名已存在");

        LevelEntity level = LevelConvert.INSTANCE.toEntity(dto);
        return levelDao.insert(level);
    }

    public int delete(List<Long> ids) {
        return levelDao.deleteBatchIds(ids);
    }

    public int update(LevelDTO dto) {
        levelDao.validateUnique(dto.getId(), LevelEntity::getName, dto.getName(), "等级名已存在");

        LevelEntity level = LevelConvert.INSTANCE.toEntity(dto);
        return levelDao.updateById(level);
    }

    public LevelDTO getById(Long id) {
        LevelEntity level = levelDao.selectById(id);
        if (level == null) {
            throw new ServiceException("会员等级不存在");
        }
        return LevelConvert.INSTANCE.toDTO(level);
    }

    public PageData<LevelPageVO> getPage(LevelPageDTO dto) {
        Page<LevelEntity> page = new Page<>(dto.getPageNum(), dto.getPageSize());

        LambdaQueryWrapperPlus<LevelEntity> wrapperPlus = new LambdaQueryWrapperPlus<>();
        wrapperPlus.likeIfPresent(LevelEntity::getName, dto.getName());

        levelDao.selectPage(page, wrapperPlus);

        List<LevelPageVO> list = page.getRecords().stream()
                .map(LevelConvert.INSTANCE::toPageVO)
                .toList();
        return new PageData<>(page, list);
    }

}
