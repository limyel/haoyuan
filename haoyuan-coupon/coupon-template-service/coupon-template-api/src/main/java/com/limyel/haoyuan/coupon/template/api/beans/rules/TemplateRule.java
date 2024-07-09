package com.limyel.haoyuan.coupon.template.api.beans.rules;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 优惠券模板规则
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemplateRule {

    // 可以享受的折扣
    private Discount discount;

    // 每人最多可领券数量
    private Integer limitation;

    // 过期时间
    private LocalDateTime deadline;

}
