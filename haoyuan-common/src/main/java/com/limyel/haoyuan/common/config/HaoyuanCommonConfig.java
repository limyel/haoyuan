package com.limyel.haoyuan.common.config;

import com.limyel.haoyuan.common.util.SpringContextUtil;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class HaoyuanCommonConfig {

    @Bean
    public SpringContextUtil springContextUtil() {
        return new SpringContextUtil();
    }

}
