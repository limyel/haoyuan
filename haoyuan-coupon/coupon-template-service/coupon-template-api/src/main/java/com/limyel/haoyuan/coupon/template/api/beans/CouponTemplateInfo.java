package com.limyel.haoyuan.coupon.template.api.beans;

import com.limyel.haoyuan.coupon.template.api.beans.rules.TemplateRule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 创建优惠券模板
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CouponTemplateInfo {

    private Long id;

    // 优惠券名称
    @NotBlank
    private String name;

    // 优惠券描述
    @NotBlank
    private String desc;

    // 优惠券类型
    @NotNull
    private Integer type;

    // 优惠券适用门店，若为空则全店通用
    private Long shopId;

    // 优惠券使用规则
    @NotNull
    private TemplateRule rule;

    // 状态
    private Integer status;

}
