package com.zenlin.common;

import com.zenlin.common.utils.DateTimeUtil;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * @Project spring-boot-all
 * @Package com.zenlin.common
 * @ClassName DateTimeUtilTest
 * @Author ZENLIN
 * @Date 2018-06-24 14:28
 * @Description TODO
 * @Version
 * @Modified By
 */
public class DateTimeUtilTest {
    DateTimeUtil dateTimeUtil = new DateTimeUtil();

    @Test
    public void getStringOfDateTimeTest() {
        LocalDateTime localDateTime = LocalDateTime.now();
        String stringOfDateTime = dateTimeUtil.getStringOfDateTime(localDateTime);
        System.out.println("stringOfDateTime = " + stringOfDateTime);
        String stringOfDateTime1 = dateTimeUtil.getStringOfDateTime(localDateTime, DateTimeUtil.YYYY_MM_DD);
        System.out.println("localDateToString = " + stringOfDateTime1);
    }

    @Test
    public void parseStringToDateTimeTest() {
        LocalDateTime localDateTime = dateTimeUtil.getDateTimeOfString("2018-01-01", DateTimeUtil.YYYY_MM_DD);
        System.out.println("localDateTime = " + localDateTime);
        LocalDateTime localDateTime1 = dateTimeUtil.getDateTimeOfString("2018-01-01 10:00:00");
        System.out.println("localDateTime1 = " + localDateTime1);
    }

    @Test
    public void getNowStringOfPatternTest() {
        String getNowStringOfPattern = dateTimeUtil.getNowStringOfPattern(DateTimeUtil.YYYY_MM_DD_HH_MM);
        System.out.println("getNowStringOfPattern = " + getNowStringOfPattern);
    }

    @Test
    public void getNowDateTimeStringTest() {
        String nowDateTimeString = dateTimeUtil.getNowDateTimeString();
        System.out.println("nowDateTimeString = " + nowDateTimeString);
    }

    @Test
    public void getNowDateStringStringTest() {
        String getNowDateString = dateTimeUtil.getNowDateString();
        System.out.println("getNowDateString = " + getNowDateString);
    }

    @Test
    public void getNowTimeStringTest() {
        String getNowTimeString = dateTimeUtil.getNowTimeString();
        System.out.println("getNowTimeString = " + getNowTimeString);
    }

    @Test
    public void parseAnotherPattern() {
        String time = "2018-01-01 12";
        String diffFormat = dateTimeUtil.parseAnotherPattern(time, DateTimeUtil.YYYY_MM_DD_HH, DateTimeUtil.YYYY_MM_DD_HH_MM_SS);
        System.out.println("diffFormat = " + diffFormat);
    }

    @Test
    public void getTimestampOfDateTimeTest() {
        long timestampOfDateTime = dateTimeUtil.getTimestampOfDateTime(LocalDateTime.now().withNano(0));
        System.out.println("timestampOfDateTime = " + timestampOfDateTime);
    }

    @Test
    public void getTimestampOfStringTest() {
        long timestampOfString = dateTimeUtil.getTimestampOfString("2018-01-01 01:01:01");
        System.out.println("timestampOfDateTime = " + timestampOfString);
        String timeString = "2018-01-01 01";
        long timestampOfString1 = dateTimeUtil.getTimestampOfString(timeString, DateTimeUtil.YYYY_MM_DD_HH);
        System.out.println("timestampOfDateTime = " + timestampOfString1);
    }

    @Test
    public void getDateTimeOfTimestampTest() {
        long timestampOfDateTime = dateTimeUtil.getTimestampOfDateTime(LocalDateTime.now());
        LocalDateTime dateTimeOfTimestamp = dateTimeUtil.getDateTimeOfTimestamp(timestampOfDateTime);
        System.out.println("dateTimeOfTimestamp = " + dateTimeOfTimestamp);
    }

    @Test
    public void getStringOfTimestampTest() {
        long time = System.currentTimeMillis();
        String stringOfTimestamp1 = dateTimeUtil.getStringOfTimestamp(time);
        System.out.println("stringOfTimestamp1 = " + stringOfTimestamp1);
        String stringOfTimestamp = dateTimeUtil.getStringOfTimestamp(time, DateTimeUtil.YYYY_MM_DD);
        System.out.println("stringOfTimestamp = " + stringOfTimestamp);
    }

    @Test
    public void getFirstDayOfNowMonthTest() {
        String firstDayOfNowMonth = dateTimeUtil.getFirstDayOfNowMonth();
        System.out.println("firstDayOfNowMonth = " + firstDayOfNowMonth);
        String firstDayOfNowMonth1 = dateTimeUtil.getFirstDayOfNowMonth("yyyy-MM-dd 00:00:00");
        System.out.println("firstDayOfNowMonth = " + firstDayOfNowMonth1);
    }

    @Test
    public void getStringOfLocalDateTest() {
        LocalDate localDate = LocalDate.now();
        String localDateToString = dateTimeUtil.getStringOfLocalDate(localDate, DateTimeUtil.YYYY_MM);
        System.out.println("localDateToString = " + localDateToString);
    }

    @Test
    public void getLastDayOfNowMonthTest() {
        String lastDayOfNowMonth = dateTimeUtil.getLastDayOfNowMonth();
        System.out.println("lastDayOfNowMonth = " + lastDayOfNowMonth);
        String lastDayOfNowMonth1 = dateTimeUtil.getLastDayOfNowMonth("yyyy-MM-dd 23:59");
        System.out.println("lastDayOfNowMonth = " + lastDayOfNowMonth1);
    }

    @Test
    public void getFirstDayOfNowYearTest() {
        String firstDayOfNowYear = dateTimeUtil.getFirstDayOfNowYear();
        System.out.println("firstDayOfNowYear = " + firstDayOfNowYear);
        String firstDayOfNowYear1 = dateTimeUtil.getFirstDayOfNowYear("yyyy-MM-dd 00");
        System.out.println("firstDayOfNowYear = " + firstDayOfNowYear1);
    }

    @Test
    public void getLastDayOfNowYearTest() {
        String lastDayOfNowYear = dateTimeUtil.getLastDayOfNowYear();
        System.out.println("lastDayOfNowYear = " + lastDayOfNowYear);
        String lastDayOfNowYear1 = dateTimeUtil.getLastDayOfNowYear("yyyy-MM-dd 00");
        System.out.println("lastDayOfNowYear = " + lastDayOfNowYear1);
    }
    @Test
    public void stringMinusOrPlusSecondTest() {
        String time = "2018-01-01 00:00:00";
        int second = 10;
        String stringMinusOrPlusSecond = dateTimeUtil.stringMinusOrPlusSecond(time, second);
        System.out.println("stringMinusOrPlusSecond = " + stringMinusOrPlusSecond);
        time="20180101121212";
        String  pattern = "yyyyMMddHHmmss";
        stringMinusOrPlusSecond = dateTimeUtil.stringMinusOrPlusSecond(time, second, pattern);
        System.out.println("stringMinusOrPlusSecond = " + stringMinusOrPlusSecond);
    }
    @Test
    public void stringMinusOrPlusMinuteTest() {
        String time = "2018-01-01 00:00:00";
        int minute = 10;
        String stringMinusOrPlusMinute = dateTimeUtil.stringMinusOrPlusMinute(time, minute);
        System.out.println("stringMinusOrPlusMinute = " + stringMinusOrPlusMinute);
        time="20180101121212";
        String  pattern = "yyyyMMddHHmmss";
        stringMinusOrPlusMinute = dateTimeUtil.stringMinusOrPlusMinute(time, minute, pattern);
        System.out.println("stringMinusOrPlusMinute = " + stringMinusOrPlusMinute);
    }

    @Test
    public void stringMinusOrPlusHourTest() {
        String time = "2018-01-01 00:00:00";
        int hour = 10;
        String stringMinusOrPlusHour = dateTimeUtil.stringMinusOrPlusHour(time, hour);
        System.out.println("stringMinusOrPlusHour = " + stringMinusOrPlusHour);
        time="20180101121212";
        String  pattern = "yyyyMMddHHmmss";
        stringMinusOrPlusHour = dateTimeUtil.stringMinusOrPlusHour(time, hour, pattern);
        System.out.println("stringMinusOrPlusHour = " + stringMinusOrPlusHour);
    }
}
