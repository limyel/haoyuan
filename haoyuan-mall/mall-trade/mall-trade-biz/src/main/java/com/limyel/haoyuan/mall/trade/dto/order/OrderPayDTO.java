package com.limyel.haoyuan.mall.trade.dto.order;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class OrderPayDTO {

    @NotBlank(message = "订单编号不能为空")
    private String orderSn;

    @NotNull(message = "支付方式不能为空")
    @Range(min = 0, max = 2, message = "支付方式错误")
    private Integer paymentMethod;

}
