package com.limyel.haoyuan.common.core.web;


import com.limyel.haoyuan.common.core.config.properties.WebProperties;
import com.limyel.haoyuan.common.core.pojo.R;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

@RequiredArgsConstructor
public class AutoPrefixUrlMapping extends RequestMappingHandlerMapping {

    private final WebProperties properties;

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
        String controllerPackage = properties.getControllerPackage();
        if (packageName.contains(controllerPackage)) {
            dotPath = packageName.replaceAll(".*" + controllerPackage, "");
        }
        return dotPath.replace(".", "/");
    }

}