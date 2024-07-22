package com.limyel.haoyuan.platform.core.autoconfig;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.limyel.haoyuan.platform.core.util.lang.time.DateFormatUtils;
import com.limyel.haoyuan.platform.core.util.lang.time.TimestampSerializer;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 在默认的 Jackson 自动装配之前覆盖，注册更合适的 ObjectMapper。
 */
@AutoConfiguration(before = {org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration.class,
        HttpMessageConvertersAutoConfiguration.class})
public class JacksonAutoConfig {

    @Bean
    @Primary
    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
        // JDK8 时间类序列化
        JavaTimeModule javaTimeModule = buildJavaTimeModule();

        //创建ObjectMapper
        ObjectMapper objectMapper = builder
                .createXmlMapper(false)
                .modulesToInstall(javaTimeModule)
                .build();
        //设置地点为中国
        objectMapper.setLocale(Locale.CHINA);
        //去掉默认的时间戳格式
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        //设置为中国上海时区
        objectMapper.setTimeZone(TimeZone.getTimeZone(ZoneId.systemDefault()));
        //序列化时，日期的统一格式
        objectMapper.setDateFormat(new SimpleDateFormat(DateFormatUtils.DEFAULT_DATETIME_FORMAT, Locale.CHINA));
        //序列化处理
        objectMapper.configure(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(), true);
        objectMapper.configure(JsonReadFeature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER.mappedFeature(), true);
        objectMapper.findAndRegisterModules();
        //失败处理
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        // 忽略没有接受的未知字段
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //单引号处理
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        //反序列化时，属性不存在的兼容处理
        objectMapper.getDeserializationConfig().withoutFeatures(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        return objectMapper;
    }

    /**
     * 时间序列化和反序列化规则，对 JDK8 之后的时间类提供支持
     * @return
     */
    private JavaTimeModule buildJavaTimeModule() {
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new TimestampSerializer.LocalDateTimeSerializer());
        javaTimeModule.addDeserializer(LocalDateTime.class, new TimestampSerializer.LocalDateTimeDeserializer());
        javaTimeModule.addSerializer(LocalDate.class, new TimestampSerializer.LocalDateSerializer());
        javaTimeModule.addDeserializer(LocalDate.class, new TimestampSerializer.LocalDateDeserializer());
        javaTimeModule.addSerializer(LocalTime.class, new TimestampSerializer.LocalTimeSerializer());
        javaTimeModule.addDeserializer(LocalTime.class, new TimestampSerializer.LocalTimeDeserializer());

        return javaTimeModule;
    }

}
