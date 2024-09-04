package com.limyel.haoyuan.framework.spring.context;

import java.util.EventObject;

/**
 * todo ?
 */
public class ApplicationEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public ApplicationEvent(Object source) {
        super(source);
    }
}
