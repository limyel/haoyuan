package com.limyel.haoyuan.coupon.template.api.beans.rules;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Discount {

    /**
     * 对于满减券 - quota 是减掉的钱数，单位是分
     * 对于打折 - quota 是折扣（100 表示原价），90 表示打 9 折
     * 对于随机立减 - quota 是最高随机立减额
     * 对于晚间特别优惠券 - quota 是日间优惠额，晚间优惠翻倍
     */
    private Long quota;

    // 订单最低要达到多少金额才能用券
    private Long threshold;

}
