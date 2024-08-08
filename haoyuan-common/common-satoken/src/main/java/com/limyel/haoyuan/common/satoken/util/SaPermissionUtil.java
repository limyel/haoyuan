package com.limyel.haoyuan.common.satoken.util;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;

@RequiredArgsConstructor
public class SaPermissionUtil {

    private final RedisTemplate<String, Object> redisTemplate;

}
