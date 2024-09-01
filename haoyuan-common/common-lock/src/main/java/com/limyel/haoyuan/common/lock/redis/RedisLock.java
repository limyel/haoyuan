package com.limyel.haoyuan.common.lock.redis;

import com.limyel.haoyuan.common.lock.AbstrackLock;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;

@RequiredArgsConstructor
public class RedisLock extends AbstrackLock {

    private final StringRedisTemplate redisTemplate;

    @Override
    public void lock() {
        while (true) {
            Boolean result = redisTemplate.opsForValue().setIfAbsent("haoyuan-lock", "1");
            if (result != null && result) {
                break;
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void unlock() {
        redisTemplate.delete("haoyuan-lock");
    }
}
