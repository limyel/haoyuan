package com.limyel.haoyuan.common.core.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.util.StringValueResolver;

public class SpringContextUtil implements ApplicationContextAware, EmbeddedValueResolverAware {

    public static ApplicationContext applicationContext;

    private static StringValueResolver valueResolver;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        SpringContextUtil.valueResolver = resolver;
    }

    public static <T> T getBean(String name) {
        return (T) applicationContext.getBean(name);
    }

    public static <T> T getBean(Class<T> type) {
        return applicationContext.getBean(type);
    }

    public static String getProperty(String key) {
        return valueResolver.resolveStringValue(key);
    }

    public static boolean containsBean(String beanName) {
        return applicationContext.containsBean(beanName);
    }
}