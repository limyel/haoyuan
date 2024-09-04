package com.limyel.haoyuan.framework.spring.context;

import com.limyel.haoyuan.framework.spring.beans.BeanFactory;
import com.limyel.haoyuan.framework.spring.beans.SimpleBeanFactory;
import com.limyel.haoyuan.framework.spring.beans.XmlBeanDefinitionReader;
import com.limyel.haoyuan.framework.spring.beans.exception.BeansException;
import com.limyel.haoyuan.framework.spring.core.ClassPathXmlResource;
import com.limyel.haoyuan.framework.spring.core.Resource;

/**
 * 根据 xml 文件扫描 Bean 的 IoC 容器
 * 将资源解析、BeanFactory 等结合起来
 */
public class ClassPathXmlApplicationContext {

    private BeanFactory beanFactory;

    public ClassPathXmlApplicationContext(String filename) {
        SimpleBeanFactory beanFactory = new SimpleBeanFactory();
        // 读取 xml 资源
        Resource resource = new ClassPathXmlResource(filename);
        // 解析 xml 资源
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions(resource);
        this.beanFactory = beanFactory;
    }

    public Object getBean(String beanName) throws BeansException {
        return beanFactory.getBean(beanName);
    }

    public Boolean containsBean(String name) {
        return beanFactory.containsBean(name);
    }

    public void registerBean(String beanName, Object obj) {
        beanFactory.registerBean(beanName, obj);
    }

}
