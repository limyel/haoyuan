package com.limyel.haoyuan.common.redis.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StringUtils;

@Slf4j
@AutoConfiguration
@RequiredArgsConstructor
public class RedissonAutoConfig {

    private final RedisProperties redisProperties;

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        SingleServerConfig singleServerConfig = config.useSingleServer();
        singleServerConfig.setAddress(String.format("redis://%s:%d", redisProperties.getHost(), redisProperties.getPort()));
        singleServerConfig.setDatabase(redisProperties.getDatabase());
        singleServerConfig.setConnectionMinimumIdleSize(redisProperties.getLettuce().getPool().getMinIdle());
        if (StringUtils.hasText(redisProperties.getPassword())) {
            singleServerConfig.setPassword(redisProperties.getPassword());
        }
        return Redisson.create(config);
    }

}
