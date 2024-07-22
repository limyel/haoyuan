package com.limyel.haoyuan.platform.core.util.result;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.mapstruct.ap.shaded.freemarker.ext.beans._BeansAPI;
import org.springframework.util.Assert;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Data
@NoArgsConstructor
public class ResultVO<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 7779160236865530179L;

    private T data;

    private Integer code;

    private String msg;

    // 额外可以扩展的数据域
    private Map<String, Object> ext;

    private String requestId;

    public ResultVO<?> generateRequestId() {
        this.requestId = UUID.randomUUID().toString().replace("-", "");
        return this;
    }

    public ResultVO<?> addExt(String key, Object value) {
        Assert.notNull(key, "ResultVO extra key must be not null!");
        if (this.ext == null) {
            this.ext = new HashMap<>();
        }
        this.ext.put(key, value);
        return this;
    }

    public boolean isSuccess() {
        return ResultCodeEnum.OK.code.equals(code);
    }

    public ResultVO(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResultVO(ResultCodeEnum resultCode) {
        this.code = resultCode.code;
        this.msg = resultCode.msg;
    }

    public ResultVO(T data, ResultCodeEnum resultCode) {
        this(resultCode);
        this.data = data;
    }

    public static <T> ResultVO<T> of(Integer code, String msg, T data) {
        return new ResultVO<>(code, msg, data);
    }

    public static ResultVO<?> success() {
        return SUCCESS;
    }

    public static <T> ResultVO<T> success(T data) {
        return new ResultVO<>(data, ResultCodeEnum.OK);
    }

    public static ResultVO<?> error() {
        return ERROR;
    }

    public static ResultVO<?> error(String msg) {
        return new ResultVO<>(ResultCodeEnum.SERVER_ERROR.code, msg, null);
    }

    public static <T> ResultVO<T> error(T data) {
        return new ResultVO<>(data, ResultCodeEnum.SERVER_ERROR);
    }

    public static <T> ResultVO<T> error(String msg, T data) {
        return new ResultVO<>(ResultCodeEnum.SERVER_ERROR.code, msg, data);
    }

    public static ResultVO<?> valueOf(boolean flag) {
        return flag ? ResultVO.success() : ResultVO.error();
    }

    public static ResultVO<?> valueOf(boolean flag, String msg) {
        return flag ? ResultVO.success() : ResultVO.error(msg);
    }

    private static final ResultVO<?> SUCCESS = new ResultVO<>(ResultCodeEnum.OK);
    private static final ResultVO<?> ERROR = new ResultVO<>(ResultCodeEnum.SERVER_ERROR);

}
