package com.limyel.haoyuan.common.util;

import java.util.HashMap;
import java.util.Map;

public class ThreadLocalUtil {

    private ThreadLocalUtil() {
    }

    private static final ThreadLocal<Map<String, Object>> THREAD_CONTEXT = ThreadLocal.withInitial(
            () -> new HashMap<>(8)
    );

    public static void put(String key, Object obj) {
        THREAD_CONTEXT.get().put(key, obj);
    }

    public static Object get(String key) {
        return THREAD_CONTEXT.get().get(key);
    }

    public static <T> T get(String key, Class<T> type) {
        Object o = get(key);
        return type.cast(o);
    }

    public static Object remove(String key) {
        return THREAD_CONTEXT.get().remove(key);
    }

    public static void clear() {
        THREAD_CONTEXT.get().clear();
    }

    public static void clearAll() {
        THREAD_CONTEXT.remove();
    }

}
