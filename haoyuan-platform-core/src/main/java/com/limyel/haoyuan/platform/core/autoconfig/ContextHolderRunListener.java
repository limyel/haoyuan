package com.limyel.haoyuan.platform.core.autoconfig;

import com.limyel.haoyuan.platform.core.util.reflect.ReflectUtils;
import com.limyel.haoyuan.platform.core.util.spring.ApplicationContextHolder;
import com.limyel.haoyuan.platform.core.util.spring.EnvironmentHolder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import java.beans.Introspector;
import java.util.Set;

public class ContextHolderRunListener implements SpringApplicationRunListener {

    private static SpringApplication springApplication;

    public ContextHolderRunListener(SpringApplication application, String[] args) {
        // todo ?
        Set<Class<?>> primarySources = (Set<Class<?>>) ReflectUtils.getFieldValue(application, "primarySources");
        for (Class<?> clazz : primarySources) {
            if (clazz.isAnnotationPresent(SpringBootApplication.class)) {
                ContextHolderRunListener.springApplication = application;
                return;
            }
        }
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        // 兼容 Spring Cloud 应用，避免多次创建 ApplicationContext 被覆盖的情况
        if (context instanceof ServletWebServerApplicationContext) {
            ApplicationContextHolder.setContext(context);
            EnvironmentHolder.setEnvironment(context.getEnvironment());
            return;
        }
        // 兼容 JUnit 单元测试
        // todo ?
        Set<Class<?>> primarySources = (Set<Class<?>>) ReflectUtils.getFieldValue(springApplication, "primarySources");
        for (Class<?> primarySource : primarySources) {
            if (primarySource.getSimpleName().equals("BootstrapImportSelectorConfiguration")) {
                continue;
            }
            if (context.containsBeanDefinition(Introspector.decapitalize(primarySource.getSimpleName()))) {
                ApplicationContextHolder.setContext(context);
                EnvironmentHolder.setEnvironment(context.getEnvironment());
                return;
            }
        }
    }


}
