package com.zenlin.common.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;

/**
 * @Project spring-boot-all
 * @Package com.zenlin.common.utils
 * @ClassName DateTimeUtil
 * @Author ZENLIN
 * @Date 2018-06-13 19:46
 * @Description TODO
 * @Version
 * @Modified By
 */
public class DateTimeUtil {
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    public static final String YYYY_MM_DD_HH = "yyyy-MM-dd HH";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYY_MM = "yyyy-MM";
    public static final String HH_MM_SS = "HH:mm:ss";
    public static final String HH_MM = "HH:mm";
    public static final String HH = "HH";

    /**
     * 日期格式化
     *
     * @param pattern
     * @return
     */
    public static DateTimeFormatter dtf(String pattern) {
        DateTimeFormatter dtf = new DateTimeFormatterBuilder().append(DateTimeFormatter.ofPattern(pattern))
                .parseDefaulting(ChronoField.MONTH_OF_YEAR, 0)
                .parseDefaulting(ChronoField.DAY_OF_MONTH, 0)
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                .toFormatter();
        return dtf;
    }

    /**
     * 将LocalDate转为自定义的时间格式的字符串
     *
     * @param localDate
     * @param pattern
     * @return
     */
    public String getStringOfLocalDate(LocalDate localDate, String pattern) {
        String format = localDate.format(dtf(pattern));
        return format;
    }

    /**
     * 将LocalDate转为标准的日期格式的字符串
     *
     * @param localDate
     * @return
     */
    public String getStringOfLocalDate(LocalDate localDate) {
        String format = localDate.format(dtf(DateTimeUtil.YYYY_MM_DD));
        return format;
    }

    /**
     * 将LocalDateTime转为自定义的时间格式的字符串
     *
     * @param localDateTime
     * @param pattern
     * @return
     */
    public String getStringOfDateTime(LocalDateTime localDateTime, String pattern) {
        String format = localDateTime.format(dtf(pattern));
        return format;
    }

    /**
     * 将LocalDateTime转为标准的时间格式的字符串
     *
     * @param localDateTime
     * @return
     */
    public String getStringOfDateTime(LocalDateTime localDateTime) {
        String format = localDateTime.format(dtf(DateTimeUtil.YYYY_MM_DD_HH_MM_SS));
        return format;
    }


    /**
     * 将标准时间格式字符串转为LocalDateTime
     *
     * @param stringDateTime
     * @param
     * @return
     */
    public static LocalDateTime getDateTimeOfString(String stringDateTime) {
        return LocalDateTime.parse(stringDateTime, dtf(DateTimeUtil.YYYY_MM_DD_HH_MM_SS));
    }

    /**
     * 将自定义的时间格式字符串转为LocalDateTime
     *
     * @param stringDateTime
     * @param pattern
     * @return
     */
    public static LocalDateTime getDateTimeOfString(String stringDateTime, String pattern) {
        return LocalDateTime.parse(stringDateTime, dtf(pattern));
    }

    /**
     * 获取当前自定义时间格式的字符串
     *
     * @param pattern 时间格式
     * @return
     */
    public String getNowStringOfPattern(String pattern) {
        LocalDateTime localDateTime = LocalDateTime.now();
        return getStringOfDateTime(localDateTime, pattern);
    }

    /**
     * 获取当前日期时间的字符串(yyyy-MM-dd HH:mm:ss)
     *
     * @return
     */
    public String getNowDateTimeString() {
        return getNowStringOfPattern(DateTimeUtil.YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 获取当前日期的字符串(yyyy-MM-dd)
     *
     * @return
     */
    public String getNowDateString() {
        return getNowStringOfPattern(DateTimeUtil.YYYY_MM_DD);
    }

    /**
     * 获取当前时间的字符串(HH:mm:ss)
     *
     * @return
     */
    public String getNowTimeString() {
        return getNowStringOfPattern(DateTimeUtil.HH_MM_SS);
    }

    /**
     * 自定义的时间格式的字符串相互转换
     *
     * @param stringTime
     * @param oldPattern
     * @param newPattern
     * @return
     */
    public String parseAnotherPattern(String stringTime, String oldPattern, String newPattern) {
        LocalDateTime parse = getDateTimeOfString(stringTime, oldPattern);
        return getStringOfDateTime(parse, newPattern);
    }

    /**
     * 将LocalDateTime转为long类型的timestamp
     *
     * @param localDateTime
     * @return
     */
    public static long getTimestampOfDateTime(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return instant.toEpochMilli();
    }

    /**
     * 将自定义的时间格式的字符串转换为long类型的timestamp
     *
     * @param timeString
     * @param pattern
     * @return
     */
    public long getTimestampOfString(String timeString, String pattern) {
        LocalDateTime localDateTime = getDateTimeOfString(timeString, pattern);
        return getTimestampOfDateTime(localDateTime);
    }

    /**
     * 将标准的时间格式的字符串转换为long类型的timestamp
     *
     * @param timeString
     * @return
     */
    public long getTimestampOfString(String timeString) {
        LocalDateTime localDateTime = getDateTimeOfString(timeString, DateTimeUtil.YYYY_MM_DD_HH_MM_SS);
        return getTimestampOfDateTime(localDateTime);
    }

    /**
     * 将long类型的timestamp转为LocalDateTime
     *
     * @param timestamp
     * @return
     */
    public static LocalDateTime getDateTimeOfTimestamp(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

    /**
     * 将long类型的timestamp转为自定义的时间格式的字符串
     *
     * @param timestamp
     * @param pattern
     * @return
     */
    public String getStringOfTimestamp(long timestamp, String pattern) {
        LocalDateTime dateTimeOfTimestamp = getDateTimeOfTimestamp(timestamp);
        return getStringOfDateTime(dateTimeOfTimestamp, pattern);
    }

    /**
     * 将long类型的timestamp转为标准的时间格式的字符串
     *
     * @param timestamp
     * @return
     */
    public String getStringOfTimestamp(long timestamp) {
        LocalDateTime dateTimeOfTimestamp = getDateTimeOfTimestamp(timestamp);
        return getStringOfDateTime(dateTimeOfTimestamp, DateTimeUtil.YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 获取本月第一天自定义时间格式的字符串
     *
     * @param pattern
     * @return
     */
    public String getFirstDayOfNowMonth(String pattern) {
        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime = localDateTime.with(TemporalAdjusters.firstDayOfMonth());
        return getStringOfDateTime(localDateTime, pattern);
    }

    /**
     * 获取本月第一天日期格式的字符串(yyyy-MM-dd)
     *
     * @return
     */
    public String getFirstDayOfNowMonth() {
        LocalDate localDate = LocalDate.now();
        localDate = localDate.with(TemporalAdjusters.firstDayOfMonth());
        return getStringOfLocalDate(localDate);
    }

    /**
     * 获取本月最后一天日期格式的字符串(yyyy-MM-dd)
     *
     * @return
     */
    public String getLastDayOfNowMonth() {
        LocalDate localDate = LocalDate.now();
        localDate = localDate.with(TemporalAdjusters.lastDayOfMonth());
        return getStringOfLocalDate(localDate);
    }

    /**
     * 获取本月最后一天自定义时间格式的字符串
     *
     * @param pattern
     * @return
     */
    public String getLastDayOfNowMonth(String pattern) {
        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime = localDateTime.with(TemporalAdjusters.lastDayOfMonth());
        return getStringOfDateTime(localDateTime, pattern);
    }

    /**
     * 获取本年第一天日期格式的字符串(yyyy-MM-dd)
     *
     * @return
     */
    public String getFirstDayOfNowYear() {
        LocalDate localDate = LocalDate.now();
        localDate = localDate.with(TemporalAdjusters.firstDayOfYear());
        return getStringOfLocalDate(localDate);
    }

    /**
     * 获取本年第一天自定义时间格式的字符串
     *
     * @return
     */
    public String getFirstDayOfNowYear(String pattern) {
        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime = localDateTime.with(TemporalAdjusters.firstDayOfYear());
        return getStringOfDateTime(localDateTime, pattern);
    }

    /**
     * 获取本年最后一天日期格式的字符串(yyyy-MM-dd)
     *
     * @return
     */
    public String getLastDayOfNowYear() {
        LocalDate localDate = LocalDate.now();
        localDate = localDate.with(TemporalAdjusters.lastDayOfYear());
        return getStringOfLocalDate(localDate);
    }

    /**
     * 获取本年最后一天自定义时间格式的字符串
     *
     * @param pattern
     * @return
     */
    public String getLastDayOfNowYear(String pattern) {
        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime = localDateTime.with(TemporalAdjusters.lastDayOfYear());
        return getStringOfDateTime(localDateTime, pattern);
    }

    /**
     * 标准的时间格式的字符串 加|减 second
     *
     * @param stringTime
     * @param second
     * @return
     */
    public String stringMinusOrPlusSecond(String stringTime, int second) {
        LocalDateTime localDateTime = getDateTimeOfString(stringTime);
        localDateTime = localDateTime.plusSeconds(second);
        return getStringOfDateTime(localDateTime);
    }

    /**
     * 自定义时间格式的字符串 加|减 second
     *
     * @param stringTime
     * @param second
     * @return
     */
    public String stringMinusOrPlusSecond(String stringTime, int second, String pattern) {
        LocalDateTime localDateTime = getDateTimeOfString(stringTime, pattern);
        localDateTime = localDateTime.plusSeconds(second);
        return getStringOfDateTime(localDateTime, pattern);
    }

    /**
     * 标准的时间格式的字符串 加|减 minute
     *
     * @param stringTime
     * @param minute
     * @return
     */
    public String stringMinusOrPlusMinute(String stringTime, int minute) {
        LocalDateTime localDateTime = getDateTimeOfString(stringTime);
        localDateTime = localDateTime.plusMinutes(minute);
        return getStringOfDateTime(localDateTime);
    }

    /**
     * 自定义时间格式的字符串 加|减 minute
     *
     * @param stringTime
     * @param minute
     * @return
     */
    public String stringMinusOrPlusMinute(String stringTime, int minute, String pattern) {
        LocalDateTime localDateTime = getDateTimeOfString(stringTime, pattern);
        localDateTime = localDateTime.plusMinutes(minute);
        return getStringOfDateTime(localDateTime, pattern);
    }

    /**
     * 标准的时间格式的字符串 加|减 hour
     *
     * @param stringTime
     * @param hour
     * @return
     */
    public String stringMinusOrPlusHour(String stringTime, int hour) {
        LocalDateTime localDateTime = getDateTimeOfString(stringTime);
        localDateTime = localDateTime.plusHours(hour);
        return getStringOfDateTime(localDateTime);
    }

    /**
     * 自定义时间格式的字符串 加|减 hour
     *
     * @param stringTime
     * @param hour
     * @return
     */
    public String stringMinusOrPlusHour(String stringTime, int hour, String pattern) {
        LocalDateTime localDateTime = getDateTimeOfString(stringTime, pattern);
        localDateTime = localDateTime.plusHours(hour);
        return getStringOfDateTime(localDateTime, pattern);
    }
    /**
     * 标准的时间格式的字符串 加|减 day
     *
     * @param stringTime
     * @param day
     * @return
     */
    public String stringMinusOrPlusDay(String stringTime, int day) {
        LocalDateTime localDateTime = getDateTimeOfString(stringTime);
        localDateTime = localDateTime.plusDays(day);
        return getStringOfDateTime(localDateTime);
    }

    /**
     * 自定义时间格式的字符串 加|减 day
     *
     * @param stringTime
     * @param day
     * @return
     */
    public String stringMinusOrPlusDay(String stringTime, int day, String pattern) {
        LocalDateTime localDateTime = getDateTimeOfString(stringTime, pattern);
        localDateTime = localDateTime.plusDays(day);
        return getStringOfDateTime(localDateTime, pattern);
    }
}
