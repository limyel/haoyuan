package com.limyel.haoyuan.framework.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 默认单例 Bean 仓库实现
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    // 存储单例 Bean 的名称
    protected List<String> beanNames = new ArrayList<>();
    // 单例 Bean 名称和实现类的映射
    protected Map<String, Object> singletons = new ConcurrentHashMap<>(256);

    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        synchronized (singletons) {
            singletons.put(beanName, singletonObject);
            beanNames.add(beanName);
        }
    }

    @Override
    public Object getSingleton(String beanName) {
        return singletons.get(beanName);
    }

    @Override
    public boolean containsSingleton(String beanName) {
        return beanNames.contains(beanName);
    }

    @Override
    public String[] getSingletonNames() {
        return (String[]) beanNames.toArray();
    }

    protected void removeSingleton(String beanName) {
        synchronized (singletons) {
            beanNames.remove(beanName);
            singletons.remove(beanName);
        }
    }
}
