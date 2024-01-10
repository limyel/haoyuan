package com.limyel.haoyuan.framework.web.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PageParam {

    /**
     * 页码
     */
    @NotNull
    @Min(value = 1)
    private Integer pageNum = 1;

    /**
     * 每页数量
     */
    @NotNull
    @Min(value = 1)
    @Max(value = 100)
    private Integer pageSize = 10;

}
