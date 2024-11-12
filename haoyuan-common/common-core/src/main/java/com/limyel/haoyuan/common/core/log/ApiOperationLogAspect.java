package com.limyel.haoyuan.common.core.log;

import com.limyel.haoyuan.common.core.constant.StringConstant;
import com.limyel.haoyuan.common.core.util.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;

import javax.swing.text.StringContent;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Aspect
public class ApiOperationLogAspect {

    @Pointcut("@annotation(com.limyel.haoyuan.common.core.log.ApiOperationLog)")
    public void apiOperationLog() {}

    @Around("apiOperationLog()")
    public Object doAround(ProceedingJoinPoint point) throws Throwable {
        try {
            long startTime = System.currentTimeMillis();

            MDC.put("traceId", UUID.randomUUID().toString());

            // 获取被请求的类和方法
            String className = point.getTarget().getClass().getSimpleName();
            String methodName = point.getSignature().getName();

            // 请求参数
            Object[] args = point.getArgs();
            String argsJson = Arrays.stream(args).map(JSONUtil::toJson).collect(Collectors.joining(StringConstant.SPLITER));

            String desc = getApiOperationLogDescription(point);

            // 请求相关日志
            log.info("===== 请求开始: [{}], 参数: {}, 请求类: {}, 请求方法: {} =====",
                    desc, argsJson, className, methodName);

            Object result = point.proceed();

            long endTime = System.currentTimeMillis();

            // 响应日志
            log.info("====== 请求结束: [{}], 耗时: {}ms, 出参: {} =====",
                    desc, endTime - startTime, JSONUtil.toJson(result));

            return result;
        } finally {
            // 清除 MDC，避免污染其他请求的上下文
            MDC.clear();
        }
    }

    /**
     * 获取注解的描述
     * @param point
     * @return
     */
    private String getApiOperationLogDescription(ProceedingJoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();

        Method method = signature.getMethod();
        ApiOperationLog annotation = method.getAnnotation(ApiOperationLog.class);

        return annotation.desc();
    }

}
