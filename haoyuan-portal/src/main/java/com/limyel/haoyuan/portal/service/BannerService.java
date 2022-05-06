package com.limyel.haoyuan.portal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.limyel.haoyuan.portal.entity.Banner;

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
