package com.limyel.haoyuan.portal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.limyel.haoyuan.portal.entity.Category;
import com.limyel.haoyuan.portal.vo.CategoryAllVO;

/**
 * <p>
 * 分类 服务类
 * </p>
 *
 * @author limyel
 * @since 2022-04-28
 */
public interface CategoryService extends IService<Category> {

    CategoryAllVO all();

}
