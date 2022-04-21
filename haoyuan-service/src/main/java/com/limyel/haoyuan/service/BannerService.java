package com.limyel.haoyuan.service;

import com.limyel.haoyuan.entity.Banner;

import java.util.List;

public interface BannerService {

    List<Banner> listByNames(List<String> names);

}
