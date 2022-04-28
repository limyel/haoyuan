package com.limyel.haoyuan.service.impl;

import com.limyel.haoyuan.dao.TagMapper;
import com.limyel.haoyuan.entity.Tag;
import com.limyel.haoyuan.service.ITagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 标签 服务实现类
 * </p>
 *
 * @author limyel
 * @since 2022-04-28
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements ITagService {

}
