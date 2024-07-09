package com.limyel.haoyuan.coupon.template.api.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagedCouponTemplateInfo {

    public List<CouponTemplateInfo> templates;

    private int pageNum;

    private long total;

}
