package com.limyel.haoyuan.service;

import com.limyel.haoyuan.entity.GridCategory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页分类 服务类
 * </p>
 *
 * @author limyel
 * @since 2022-04-28
 */
public interface IGridCategoryService extends IService<GridCategory> {

    List<GridCategory> listAll();

}
