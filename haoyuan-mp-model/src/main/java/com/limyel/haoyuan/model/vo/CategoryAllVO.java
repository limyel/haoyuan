package com.limyel.haoyuan.model.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryAllVO {

    private List<CategoryPureVO> root;

    private List<CategoryPureVO> sub;
}
