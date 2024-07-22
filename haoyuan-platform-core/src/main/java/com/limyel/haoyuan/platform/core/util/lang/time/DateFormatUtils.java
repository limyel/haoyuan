package com.limyel.haoyuan.platform.core.util.lang.time;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 时间格式处理工具类
 */
public abstract class DateFormatUtils {

    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";
    public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String YMDHM_FORMAT = "yyyy-MM-dd HH:mm";

    public static final DateTimeFormatter DEFAULT_DATE_FORMATTER = DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT).withZone(
            ZoneId.systemDefault());
    public static final DateTimeFormatter DEFAULT_TIME_FORMATER = DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT).withZone(
            ZoneId.systemDefault());
    public static final DateTimeFormatter DEFAULT_DATETIME_FORMATER = DateTimeFormatter.ofPattern(DEFAULT_DATETIME_FORMAT)
            .withZone(ZoneId.systemDefault());
    public static final DateTimeFormatter YMDHM_FORMATER = DateTimeFormatter.ofPattern(YMDHM_FORMAT).withZone(
            ZoneId.systemDefault());

    public static String formatDate(Date date, String pattern) {
        return ZonedDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault())
                .format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String formatDate(String pattern) {
        return ZonedDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String formatDate() {
        return ZonedDateTime.now().format(DEFAULT_DATE_FORMATTER);
    }

    public static String formatDate(Date date) {
        return ZonedDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).format(DEFAULT_DATE_FORMATTER);
    }

    public static String formatTime() {
        return ZonedDateTime.now().format(DEFAULT_TIME_FORMATER);
    }

    public static String formatTime(Date time) {
        return ZonedDateTime.ofInstant(time.toInstant(), ZoneId.systemDefault()).format(DEFAULT_TIME_FORMATER);
    }

    public static String formatDatetime() {
        return ZonedDateTime.now().format(DEFAULT_DATETIME_FORMATER);
    }

    public static String formatDatetime(Date date) {
        return ZonedDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).format(DEFAULT_DATETIME_FORMATER);
    }

    public static Date parseDate(String date) {
        return Date.from(ZonedDateTime.from(DEFAULT_DATE_FORMATTER.parse(date)).toInstant());
    }

    public static Date parseDatetime(String datetime) {
        return Date.from(ZonedDateTime.from(DEFAULT_DATETIME_FORMATER.parse(datetime)).toInstant());
    }

}
