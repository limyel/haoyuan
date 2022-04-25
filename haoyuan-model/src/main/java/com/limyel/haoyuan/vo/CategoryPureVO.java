package com.limyel.haoyuan.vo;

import com.limyel.haoyuan.entity.Category;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
public class CategoryPureVO {

    private Long id;

    private String name;

    private Boolean root;

    private String img;

    private Long parentId;

    private Long sequence;

    public CategoryPureVO(Category category) {
        BeanUtils.copyProperties(category, this);
    }

}
