package com.limyel.haoyuan.framework.spring.beans;

import com.limyel.haoyuan.framework.spring.beans.exception.BeansException;

/**
 * 专注于 Bean 管理的容器
 */
public interface BeanFactory {

    Object getBean(String beanName) throws BeansException;

    Boolean containsBean(String name);

    void registerBean(String beanName, Object obj);

}
