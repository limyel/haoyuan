package com.limyel.haoyuan.framework.spring;

/**
 * 单例 Bean 仓库，用于管理单例 Bean
 */
public interface SingletonBeanRegistry {

    void registerSingleton(String beanName, Object singletonObject);

    Object getSingleton(String beanName);

    boolean containsSingleton(String beanName);

    String[] getSingletonNames();

}
