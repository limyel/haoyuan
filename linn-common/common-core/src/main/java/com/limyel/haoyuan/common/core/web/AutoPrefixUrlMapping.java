package com.limyel.haoyuan.common.core.web;

import com.limyel.haoyuan.common.core.pojo.R;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

@RequiredArgsConstructor
public class AutoPrefixUrlMapping extends RequestMappingHandlerMapping {

    public static final String CONTROLLER_PKG_NAME = "controller";

    @Override
    protected RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {
        RequestMappingInfo mappingInfo = super.getMappingForMethod(method, handlerType);
        // 响应类型为 R 的方法才会更改路由
        if (mappingInfo != null && method.getReturnType().equals(R.class)) {
            String prefix = this.getPrefix(handlerType);
            return RequestMappingInfo.paths(prefix).build().combine(mappingInfo);
        }
        return mappingInfo;
    }

    private String getPrefix(Class<?> handlerType) {
        String packageName = handlerType.getPackage().getName();
        String dotPath = packageName;
        if (packageName.contains(CONTROLLER_PKG_NAME)) {
            dotPath = packageName.replaceAll(".*" + CONTROLLER_PKG_NAME, "");
        }
        return dotPath.replace(".", "/");
    }

}