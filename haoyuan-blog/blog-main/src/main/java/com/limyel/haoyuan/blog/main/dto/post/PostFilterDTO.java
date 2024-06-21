package com.limyel.haoyuan.blog.main.dto.post;

import com.limyel.haoyuan.common.web.pojo.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class PostFilterDTO extends PageParam {

    List<String> tags;

}
