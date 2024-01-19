package com.limyel.haoyuan.common.util;

import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class ConvertUtil {

    public static final ZoneId ZONE_ID = ZoneId.systemDefault();

    public static <T> T sourceToTarget(Object source, Class<T> clazz) {
        T target;
        try {
            target = clazz.getConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException();
        }
        BeanUtils.copyProperties(source, target);
        return target;
    }

    public static <T> List<T> sourceToTarget(List<?> sourceList, Class<T> clazz) {
        return sourceToTarget(sourceList, source -> sourceToTarget(source, clazz));
    }

    public static <T, R> List<R> sourceToTarget(List<T> originList, Function<T, R> mapper) {
        if (originList == null || originList.isEmpty()) {
            return new ArrayList<>();
        }
        List<R> newList = new ArrayList<>(originList.size());
        originList.forEach(element -> {
            R newElement = mapper.apply(element);
            if (newElement != null) {
                newList.add(newElement);
            }
        });
        return newList;
    }

    public static <K, V> Map<K, V> listToMap(List<V> list, Function<V, K> keyExtractor) {
        if (list == null || list.isEmpty()) {
            return new HashMap<>();
        }
        Map<K, V> map = new HashMap<>(list.size());
        list.forEach(element -> {
            K key = keyExtractor.apply(element);
            if (key != null) {
                map.put(key, element);
            }
        });
        return map;
    }

    public static <K, V> Map<K, V> listToMap(List<V> list, Function<V, K> keyExtractor, Predicate<V> predicate) {
        if (list == null || list.isEmpty()) {
            return new HashMap<>();
        }
        Map<K, V> map = new HashMap<>(list.size());
        for (V element: list) {
            K key = keyExtractor.apply(element);
            if (key == null || !predicate.test(element)) {
                continue;
            }
            map.put(key, element);
        }
        return map;
    }

    public static <T, R> List<R> resultToList(List<T> originList, Function<T, R> mapper, Predicate<T> predicate) {
        if (originList == null || originList.isEmpty()) {
            return new ArrayList<>();
        }
        List<R> newList = new ArrayList<>(originList.size());
        originList.forEach(element -> {
            R newElement = mapper.apply(element);
            if (newElement != null || predicate.test(element)) {
                newList.add(newElement);
            }
        });
        return newList;
    }

    public static LocalDateTime toLocalDateTime(Date date) {
        return date.toInstant().atZone(ZONE_ID).toLocalDateTime();
    }

}
