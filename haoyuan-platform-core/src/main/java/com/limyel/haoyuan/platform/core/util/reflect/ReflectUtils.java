package com.limyel.haoyuan.platform.core.util.reflect;

import com.limyel.haoyuan.platform.core.enhancer.exception.PlatformException;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * todo ?
 */
public class ReflectUtils {

    /**
     * 获取指定对象的指定属性值
     * @param target
     * @param fieldName
     * @return
     */
    public static Object getFieldValue(Object target, String fieldName) {
        Field field = ReflectionUtils.findField(target.getClass(), fieldName);
        ReflectionUtils.makeAccessible(field);
        return ReflectionUtils.getField(field, target);
    }

    /**
     * 获取指定对象的指定属性值
     * @param target
     * @param fieldName
     * @param paramsType
     * @return
     * @param <T>
     */
    public static <T> T getFieldValue(Object target, String fieldName, Class<T> paramsType) {
        Field field = ReflectionUtils.findField(target.getClass(), fieldName, paramsType);
        ReflectionUtils.makeAccessible(field);
        return (T) ReflectionUtils.getField(field, target);
    }

    /**
     * 给类的静态属性设置值
     * @param target
     * @param fieldName
     * @param fieldValue
     */
    public static void setFieldValue(Object target, String fieldName, Object fieldValue) {
        Field field = ReflectionUtils.findField(target.getClass(), fieldName);
        ReflectionUtils.makeAccessible(field);
        ReflectionUtils.setField(field, target, fieldValue);
    }

    /**
     * 解析第一个接口的泛型类型
     * @param declaredClass
     * @return
     * @param <T>
     */
    public static <T> Class<T> resolveInterfaceGenericType(Class<?> declaredClass) {
        ParameterizedType parameterizedType = (ParameterizedType) declaredClass.getGenericInterfaces()[0];
        return resolveGenericType(parameterizedType);
    }

    /**
     * 解析父类的第一个泛型类型
     * @param declaredClass
     * @return
     * @param <T>
     */
    public static <T> Class<T> resolveSuperClassGenericType(Class<?> declaredClass) {
        ParameterizedType parameterizedType = (ParameterizedType) declaredClass.getGenericInterfaces()[0];
        return resolveGenericType(parameterizedType);
    }

    /**
     * 解析泛型
     * @param parameterizedType
     * @return
     * @param <T>
     */
    private static <T> Class<T> resolveGenericType(ParameterizedType parameterizedType) {
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        if (actualTypeArguments[0] instanceof Class) {
            return (Class<T>) actualTypeArguments[0];
        } else if (actualTypeArguments[0] instanceof ParameterizedType nestedType) {
            return (Class<T>) nestedType.getRawType();
        }
        throw new PlatformException("解析泛型失败！");
    }

}
