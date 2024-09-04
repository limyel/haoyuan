package com.limyel.haoyuan.framework.spring.test;

import com.limyel.haoyuan.framework.spring.beans.exception.BeansException;
import com.limyel.haoyuan.framework.spring.context.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) throws BeansException {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        MyService myService = (MyServiceImpl) applicationContext.getBean("myService");
        myService.greet();
    }
}
