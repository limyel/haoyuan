package com.limyel.haoyuan.framework.spring.beans;

import com.limyel.haoyuan.framework.spring.core.Resource;
import org.dom4j.Element;

/**
 * BeanDefinition 解析器
 */
public class XmlBeanDefinitionReader {

    private SimpleBeanFactory simpleBeanFactory;

    public XmlBeanDefinitionReader(SimpleBeanFactory simpleBeanFactory) {
        this.simpleBeanFactory = simpleBeanFactory;
    }

    /**
     * 解析 xml 文件，将配置转换为 BeanDefinition 并加载到 BeanFactory 中。
     * @param resource
     */
    public void loadBeanDefinitions(Resource resource) {
        while (resource.hasNext()) {
            Element element = (Element) resource.next();
            String beanId = element.attributeValue("id");
            String beanClassName = element.attributeValue("class");
            BeanDefinition beanDefinition = new BeanDefinition(beanId, beanClassName);
            // 注册 BeanDefinition
            this.simpleBeanFactory.registerBeanDefinition(beanDefinition);
        }
    }
}
