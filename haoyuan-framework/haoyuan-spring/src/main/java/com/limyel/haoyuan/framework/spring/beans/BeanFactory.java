package com.limyel.haoyuan.framework.spring.beans;

import com.limyel.haoyuan.framework.spring.beans.exception.BeansException;

/**
 * 专注于 Bean 的管理
 */
public interface BeanFactory {

    Object getBean(String beanName) throws BeansException;

    void registerBeanDefinition(BeanDefinition beanDefinition);

}
