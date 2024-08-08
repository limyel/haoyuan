package com.limyel.haoyuan.mall.gateway.auth;

import cn.dev33.satoken.reactor.filter.SaReactorFilter;
import cn.dev33.satoken.util.SaResult;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SaTokenConfig {

    // 注册 Sa-Token全局过滤器
    @Bean
    public SaReactorFilter saReactorFilter() {
        return new SaReactorFilter()
                // 拦截地址，拦截全部路由地址
                .addInclude("/**")
                // 鉴权方法：每次访问进入
                .setAuth(obj -> {

                })
                // 异常处理方法：每次setAuth函数出现异常时进入
                .setError(e -> {
                    e.printStackTrace();
                    return SaResult.error(e.getMessage());
                })
                ;
    }

}
