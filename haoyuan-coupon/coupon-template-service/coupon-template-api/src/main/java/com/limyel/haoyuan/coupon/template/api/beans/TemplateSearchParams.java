package com.limyel.haoyuan.coupon.template.api.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemplateSearchParams {

    private Long id;

    private String name;

    private Integer type;

    private Long shopId;

    private Integer status;

    private int pageNum;

    private int pageSize;

}
