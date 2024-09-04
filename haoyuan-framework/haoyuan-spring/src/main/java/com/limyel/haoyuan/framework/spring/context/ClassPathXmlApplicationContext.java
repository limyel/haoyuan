package com.limyel.haoyuan.framework.spring.context;

import com.limyel.haoyuan.framework.spring.beans.BeanDefinition;
import com.limyel.haoyuan.framework.spring.beans.BeanFactory;
import com.limyel.haoyuan.framework.spring.beans.SimpleBeanFactory;
import com.limyel.haoyuan.framework.spring.beans.XmlBeanDefinitionReader;
import com.limyel.haoyuan.framework.spring.beans.exception.BeansException;
import com.limyel.haoyuan.framework.spring.core.ClassPathXmlResource;
import com.limyel.haoyuan.framework.spring.core.Resource;

/**
 * 根据 xml 文件扫描 Bean 的 IoC 容器
 */
public class ClassPathXmlApplicationContext {

    private BeanFactory beanFactory;

    public ClassPathXmlApplicationContext(String filename) {
        BeanFactory beanFactory = new SimpleBeanFactory();
        Resource resource = new ClassPathXmlResource(filename);
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions(resource);
        this.beanFactory = beanFactory;
    }

    public Object getBean(String beanName) throws BeansException {
        return beanFactory.getBean(beanName);
    }

    public void registerBeanDefinition(BeanDefinition beanDefinition) {
        beanFactory.registerBeanDefinition(beanDefinition);
    }

}
