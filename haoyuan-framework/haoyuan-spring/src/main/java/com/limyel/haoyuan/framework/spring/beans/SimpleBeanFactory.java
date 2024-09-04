package com.limyel.haoyuan.framework.spring.beans;

import com.limyel.haoyuan.framework.spring.beans.exception.BeansException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleBeanFactory implements BeanFactory {

    private List<BeanDefinition> beanDefinitions = new ArrayList<>();
    private List<String> beanNames = new ArrayList<>();
    private Map<String, Object> singletons = new HashMap<>();

    public SimpleBeanFactory() {
    }

    @Override
    public Object getBean(String beanName) throws BeansException {
        // 尝试获取 Bean 实例
        Object singleton = singletons.get(beanName);
        // 没有 Bean 实例，尝试获取它的 BeanDefinition 来创建实例
        if (singleton == null) {
            int i = beanNames.indexOf(beanName);
            if (i == -1) {
                // todo
                throw new BeansException("");
            } else {
                BeanDefinition beanDefinition = beanDefinitions.get(i);
                try {
                    // 创建 Bean 实例
                    singleton = Class.forName(beanDefinition.getClassName()).newInstance();
                    // 注册 Bean 实例
                    singletons.put(beanDefinition.getId(), singleton);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return singleton;
    }

    @Override
    public void registerBeanDefinition(BeanDefinition beanDefinition) {
        this.beanDefinitions.add(beanDefinition);
        this.beanNames.add(beanDefinition.getId());
    }
}
