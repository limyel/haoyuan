package com.limyel.haoyuan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.limyel.haoyuan.common.exception.ApiException;
import com.limyel.haoyuan.dao.BannerMapper;
import com.limyel.haoyuan.entity.Banner;
import com.limyel.haoyuan.service.IBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * banner 服务实现类
 * </p>
 *
 * @author limyel
 * @since 2022-04-28
 */
@Service
public class BannerServiceImpl extends ServiceImpl<BannerMapper, Banner> implements IBannerService {

    @Autowired
    private BannerMapper bannerMapper;

    @Override
    public List<Banner> listByNames(List<String> nameList) {
        LambdaQueryWrapper<Banner> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.in(Banner::getName, nameList);
        List<Banner> banners = bannerMapper.selectList(queryWrapper);
        if (banners.size() != nameList.size()) {
            throw new ApiException(10001);
        }
        return banners;
    }

}
