package com.limyel.haoyuan.common.web.handler;


import com.limyel.haoyuan.common.web.pojo.R;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

public class AutoPrefixUrlMapping extends RequestMappingHandlerMapping {

    @Value("${haoyuan.controller-package:controller}")
    private String controllerPackage;

    @Value("${haoyuan.api-package:api}")
    private String apiPackage;

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
        if (packageName.contains(controllerPackage)) {
            dotPath = packageName.replaceAll(".*" + controllerPackage, "");
        } else if (packageName.contains(apiPackage)) {
            dotPath = packageName.replaceAll(".*" + apiPackage, "");
        }
        return dotPath.replace(".", "/");
    }

}