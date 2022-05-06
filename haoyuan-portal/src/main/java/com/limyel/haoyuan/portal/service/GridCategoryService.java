package com.limyel.haoyuan.portal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.limyel.haoyuan.portal.entity.GridCategory;

import java.util.List;

/**
 * <p>
 * 首页分类 服务类
 * </p>
 *
 * @author limyel
 * @since 2022-04-28
 */
public interface GridCategoryService extends IService<GridCategory> {

    List<GridCategory> listAll();

}
