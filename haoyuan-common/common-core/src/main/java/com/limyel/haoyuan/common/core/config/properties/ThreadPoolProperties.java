package com.limyel.haoyuan.common.core.config.properties;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

@Data
@ConditionalOnProperty(prefix = "thread-pool")
public class ThreadPoolProperties {

    /**
     * 是否开启线程池
     */
    private boolean enabled;

    /**
     * 队列最大长度
     */
    private int queueCapacity;

    /**
     * 线程允许空闲的时间（秒）
     */
    private int keepAliveSeconds;

    /**
     * 线程名前缀
     */
    private String namePrefix;

}
