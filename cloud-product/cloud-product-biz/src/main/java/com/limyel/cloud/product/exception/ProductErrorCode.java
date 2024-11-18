package com.limyel.cloud.product.exception;

import com.limyel.haoyuan.common.core.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductErrorCode implements ErrorCode {
    PRODUCT_INSERT_FAILED(40100, "商品保存失败"),
    PRODUCT_UPDATE_FAILED(40101, "商品更新失败"),
    ;

    private final Integer code;
    private final String msg;

}
