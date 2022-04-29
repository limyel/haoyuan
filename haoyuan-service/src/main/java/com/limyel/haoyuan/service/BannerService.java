package com.limyel.haoyuan.service;

import com.limyel.haoyuan.entity.Banner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * banner 服务类
 * </p>
 *
 * @author limyel
 * @since 2022-04-28
 */
public interface BannerService extends IService<Banner> {

    List<Banner> listByNames(List<String> nameList);

}
