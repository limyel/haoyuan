package com.limyel.haoyuan.blog.common.main.dto.post;

import com.limyel.haoyuan.common.core.pojo.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class PostListDTO extends PageParam {

    private List<String> tags = new ArrayList<>();

}
