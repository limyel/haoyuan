package com.limyel.haoyuan.common.satoken.config;

import cn.dev33.satoken.SaManager;
import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.filter.SaServletFilter;
import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.same.SaSameUtil;
import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpLogic;
import cn.dev33.satoken.strategy.SaStrategy;
import cn.dev33.satoken.util.SaResult;
import com.limyel.haoyuan.common.satoken.handler.SameTokenInterceptor;
import com.limyel.haoyuan.common.satoken.service.StpInterfaceImpl;
import com.limyel.haoyuan.common.satoken.service.StpUserUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@AutoConfiguration
public class SaTokenAutoConfig implements WebMvcConfigurer, InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        // 重写 Sa-Token 的注解处理器，增加注解合并功能
        SaStrategy.instance.getAnnotation = AnnotatedElementUtils::getMergedAnnotation;
    }

    /**
     * 注册 sa-token 拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        StpLogic userLogic = StpUserUtil.stpLogic;
        registry.addInterceptor(new SaInterceptor()).addPathPatterns("/**");
    }

    /**
     * 校验是否从网关转发
     * @return
     */
    @Bean
    @ConditionalOnProperty(prefix = "sa-token", name = "check-same-token", havingValue = "true")
    public SaServletFilter saServletFilter() {
        return new SaServletFilter()
                .addInclude("/**")
                .setAuth(obj -> {
                    String token = SaHolder.getRequest().getHeader(SaSameUtil.SAME_TOKEN);
                    if (SaManager.getConfig().getCheckSameToken()) {
                        SaSameUtil.checkToken(token);
                    }
                })
                .setError(e -> SaResult.error("认证失败").setCode(HttpStatus.UNAUTHORIZED.value()));
    }

    @Bean
    public StpInterface stpInterface() {
        return new StpInterfaceImpl();
    }

    @Bean
    public SameTokenInterceptor feignInterceptor() {
        return new SameTokenInterceptor();
    }

}
