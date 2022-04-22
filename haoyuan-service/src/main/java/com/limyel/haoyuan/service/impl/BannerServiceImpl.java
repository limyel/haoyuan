package com.limyel.haoyuan.service.impl;

import com.limyel.haoyuan.common.exception.ApiException;
import com.limyel.haoyuan.dao.BannerRepository;
import com.limyel.haoyuan.entity.Banner;
import com.limyel.haoyuan.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerRepository bannerRepository;

    @Override
    public List<Banner> listByNames(List<String> names) {
        List<Banner> banners = bannerRepository.findBannersByNameIn(names);
        if (banners.size() != names.size()) {
            throw new ApiException(10001);
        }
        return banners;
    }
}
