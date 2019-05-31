package com.utils.timeutils;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * Created by ZhangGang on 2018/12/1.
 */
public class TimeUtilsByLocal {
    //	private  static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	private  static SimpleDateFormat formatter_day = new SimpleDateFormat("yyyy-MM-dd");
    public final static String format = "yyyy-MM-dd HH:mm:ss";

    /**
     * @param date   日期
     * @param format 日期格式
     */
    public static String getCurrentTimeByString(LocalDateTime date, String format) {
        if (date != null) {
            String localDateTime = date.format(DateTimeFormatter.ofPattern(format));
            return localDateTime;
        } else {
            return null;
        }
    }

    /**
     * 获取当前时间
     *
     * @return 返回"yyyy-MM-dd HH:mm:ss"
     */
    public static String getCurrentTimeString() {
        String localDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern(format));
        return localDateTime;
    }

    /**
     * @param strTime 类型转换为LocalDateTime类型
     *                <p>
     *                yyyy-MM-dd HH:mm:ss
     */
    public static LocalDateTime stringToLocalDateTime(String strTime) {
        LocalDateTime date = stringToDateFormat(strTime, format);
        return date;
    }

    /**
     * @param strTime string类型转换为date类型
     * @param format  日期格式
     */
    public static LocalDateTime stringToDateFormat(String strTime, String format) {
        LocalDateTime date = LocalDateTime.parse(strTime, DateTimeFormatter.ofPattern(format));
        return date;
    }

    /*获取两个时间差，返回秒*/
    public static long getDateDiff(LocalDateTime endDate, LocalDateTime nowDate) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        long ns = 1000;
        //获取秒数
//        Long second = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
        //获取毫秒数
//        Long milliSecond = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        // 获得两个时间的毫秒时间差异
        long sec = endDate.toEpochSecond(ZoneOffset.of("+8")) - nowDate.toEpochSecond(ZoneOffset.of("+8"));
        // 计算差多少秒//输出结果
        //  long sec = diff / ns;
        return sec;
    }

    /**
     * 时间小时的加减 正数为加，负数为减
     */
    public static LocalDateTime addHour(LocalDateTime dateOper, int hour) {
        try {
            return dateOper.plusHours(hour);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    // 获得某天最大时间 2017-10-15 23:59:59
    public static LocalDateTime getEndOfDay( LocalDateTime localDateTime) {
        return localDateTime.with(LocalTime.MAX);
    }

    // 获得某天最小时间 2017-10-15 00:00:00
    public static LocalDateTime getStartOfDay(LocalDateTime localDateTime) {
        return localDateTime.with(LocalTime.MIN);
    }
    public static Date getStartOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        return Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    //把毫秒数转换为标准日期时间字符串
    public String formatMilliSecond(long milliSeconds) {
        ZoneId z = ZoneId.systemDefault();
        Instant instant = Instant.now();
        LocalDateTime datetime = LocalDateTime.ofEpochSecond(milliSeconds / 1000, 0, z.getRules().getOffset(instant));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return datetime.format(formatter);
    }

    //获取当前日期时间字符串
    public String getNowDateAndTimeString() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }

    //获取当前日期字符串
    public String getNowDateString() {
        LocalDate today = LocalDate.now();
        return today.toString();
    }

    //获取当前时间字符串
    public String getNowTimeString() {
        LocalTime now = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return now.format(formatter);
    }

    //获取某年某月第一天
    public String getYearMonthFirstDayString(int year, int month) throws Exception {
        if (month < 1 || month > 12) {
            throw new Exception("invalid parameters");
        }
        Integer iyear = year;
        Integer imonth = month;
        if (month < 10) {
            return iyear.toString() + "-0" + imonth.toString() + "-01 00:00:00";
        } else {
            return iyear.toString() + "-" + imonth.toString() + "-01 00:00:00";
        }
    }

    //获取某年某月最后一天
    public String getYearMonthLastDayString(int year, int month) throws Exception {
        if (month < 1 || month > 12) {
            throw new Exception("invalid parameters");
        }
        LocalDate date = LocalDate.of(year, month, 1);
        Integer lastday = date.getMonth().length(date.isLeapYear());
        Integer iyear = year;
        Integer imonth = month;
        if (month < 10) {
            return iyear.toString() + "-0" + imonth.toString() + "-" + lastday.toString() + " 23:59:59";
        } else {
            return iyear.toString() + "-" + imonth.toString() + "-" + lastday.toString() + " 23:59:59";
        }
    }

    //获取某年某月某日所在周的某一天
    public String getWeekDayString(int year, int month, int day, DayOfWeek dow) throws Exception {
        try {
            LocalDate date = LocalDate.of(year, month, day);
            LocalDate newDate = date.with(TemporalAdjusters.nextOrSame(dow));
            return newDate.toString();
        } catch (Exception e) {
            throw new Exception("invalid parameters");
        }
    }

    public static void main(String[] args) {
        System.out.println(getStartOfDay(LocalDateTime.now()));
        System.out.println(getEndOfDay(LocalDateTime.now()));
        System.out.println(getStartOfDay(new Date()));
    }
}
