package com.limyel.haoyuan.framework.spring.beans;

import com.limyel.haoyuan.framework.spring.DefaultSingletonBeanRegistry;
import com.limyel.haoyuan.framework.spring.beans.exception.BeansException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 简单的 BeanFactory 实现，具备单例 Bean 管理能力
 */
public class SimpleBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    private Map<String, BeanDefinition> beanDefinitions = new ConcurrentHashMap<>(256);

    public SimpleBeanFactory() {
    }

    @Override
    public Object getBean(String beanName) throws BeansException {
        // 尝试获取 Bean 实例
        Object singleton = getSingleton(beanName);
        // 没有 Bean 实例，尝试获取它的 BeanDefinition 来创建实例
        if (singleton == null) {
            BeanDefinition beanDefinition = beanDefinitions.get(beanName);
            if (beanDefinition == null) {
                throw new BeansException("No bean.");
            }
            try {
                singleton = Class.forName(beanDefinition.getClassName()).newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
            registerSingleton(beanName, singleton);
        }
        return singleton;
    }

    @Override
    public void registerBean(String beanName, Object obj) {
        registerSingleton(beanName, obj);
    }

    public void registerBeanDefinition(BeanDefinition beanDefinition) {
        beanDefinitions.put(beanDefinition.getId(), beanDefinition);
    }

    public Boolean containsBean(String name) {
        return containsSingleton(name);
    }

}
