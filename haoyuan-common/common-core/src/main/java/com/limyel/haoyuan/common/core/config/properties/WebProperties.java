package com.limyel.haoyuan.common.core.config.properties;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

@Data
@ConditionalOnProperty
public class WebProperties {

    /**
     * 控制层的报名
     */
    private String controllerPackage = "controller";

}
