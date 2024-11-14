package com.limyel.haoyuan.common.cloud.feign;

import com.limyel.haoyuan.common.core.exception.GlobalErrorCode;
import com.limyel.haoyuan.common.core.pojo.R;
import feign.FeignException;
import feign.Response;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.springframework.cloud.openfeign.support.SpringDecoder;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

@RequiredArgsConstructor
public class FeignDecoder implements Decoder {

    private final SpringDecoder decoder;

    @Override
    public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {
        Method method = response.request().requestTemplate().methodMetadata().method();
        boolean isR = method.getReturnType() == R.class;
        if (!isR) {
            Type newType = new ParameterizedType() {
                @Override
                public Type[] getActualTypeArguments() {
                    return new Type[]{type};
                }

                @Override
                public Type getRawType() {
                    return R.class;
                }

                @Override
                public Type getOwnerType() {
                    return null;
                }
            };
            R<?> result = (R<?>) this.decoder.decode(response, newType);
            if (GlobalErrorCode.SUCCESS.getCode().equals(result.getCode())) {
                return result.getData();
            } else {
                throw new DecodeException(HttpStatus.SC_INTERNAL_SERVER_ERROR, result.getMsg(), response.request());
            }
        }
        return this.decoder.decode(response, type);
    }
}
