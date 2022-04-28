package com.limyel.haoyuan.service;

import com.limyel.haoyuan.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import com.limyel.haoyuan.vo.CategoryAllVO;

/**
 * <p>
 * 分类 服务类
 * </p>
 *
 * @author limyel
 * @since 2022-04-28
 */
public interface ICategoryService extends IService<Category> {

    CategoryAllVO all();

}
