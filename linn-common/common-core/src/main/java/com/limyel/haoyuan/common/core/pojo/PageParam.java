package com.limyel.haoyuan.common.core.pojo;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
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