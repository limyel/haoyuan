package com.limyel.haoyuan.mall.product.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.limyel.haoyuan.common.core.pojo.PageParam;
import com.limyel.haoyuan.common.mybatis.pojo.PageData;
import com.limyel.haoyuan.mall.product.convert.UserSpuConvert;
import com.limyel.haoyuan.mall.product.dao.UserSpuDao;
import com.limyel.haoyuan.mall.product.entity.UserSpuEntity;
import com.limyel.haoyuan.mall.product.vo.spu.UserSpuVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserSpuService {

    private final UserSpuDao userSpuDao;

    public PageData<UserSpuVO> getList(PageParam pageParam) {
        Page<UserSpuEntity> page = new Page<>(pageParam.getPageNum(), pageParam.getPageSize());

        userSpuDao.selectPage(page, null);

        List<UserSpuVO> list = page.getRecords().stream()
                .map(UserSpuConvert.INSTANCE::toVO)
                .toList();
        return new PageData<>(page, list);
    }
}
