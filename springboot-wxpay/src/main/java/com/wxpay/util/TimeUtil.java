package com.wxpay.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Chenzq on 2018/3/1.
 */
public class TimeUtil {
    public final static String format = "yyyy-MM-dd HH:mm:ss";
    public final static String formatWithSlash = "yyyy/MM/dd HH:mm:ss";
    public final static String formatWithHM = "yyyy-MM-dd HH:mm";
    public final static String formatWithYMD = "yyyy-MM-dd";

    public static String toString(Date time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(time);
    }

    public static String toString(Date time, String format) {

        SimpleDateFormat sdf = new SimpleDateFormat(format);

        return sdf.format(time);
    }

    public static Date getNow() {

        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    /**
     * 计算当前日期是周几
     * @param calendar
     * @return
     */
    public static int getDayOfWeek(Calendar calendar){
        //一周第一天是否为星期天
        boolean isFirstSunday = (calendar.getFirstDayOfWeek() == Calendar.SUNDAY);
        //获取周几
        int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
        //若一周第一天为星期天，则-1
        if(isFirstSunday){
            weekDay = weekDay - 1;
            if(weekDay == 0){
                weekDay = 7;
            }
        }

        return weekDay;
    }

    public static Date getDateByString(String str, String formatStr) throws ParseException {
        if (str == null) {
            return null;
        }
        try {
            return getDateByStringAct(str, formatStr);
        } catch (ParseException e) {
            try {
                return getDateByStringAct(str, format);
            } catch (ParseException e1) {
                return getDateByStringAct(str, formatWithSlash);
            }
        }
    }

    public static Date getDateByStringAct(String str, String formatStr) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(formatStr/*"yyyy-MM-dd HH:mm:ss"*/);
        Date date = null;
        date = format.parse(str);
        return date;
    }

    public static Date addSecond(Date date,int second){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.SECOND, second);
        date = c.getTime();
        return date;
    }

    public static String getStringByDate(Date date,String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static Date getBeginTimeOfDay() throws Exception{
        String str = getStringByDate(new Date(),"yyyy-MM-dd");
        return getDateByString(str + " 00:00:00",format);
    }

    public static String gethHms(Date date) {
        return getStringByDate(date,formatWithHM).split(" ")[1];
    }

    public static Date addDay(Date dateFrom, int i) {
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
//		Date beginDate = new Date();
        Calendar date = Calendar.getInstance();
        date.setTime(dateFrom);
        date.set(Calendar.DATE, date.get(Calendar.DATE) + i);
        Date endDate = null;
        try {
            endDate = dft.parse(dft.format(date.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return endDate;
    }

    public static Date addHour(Date date, int hours) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR_OF_DAY, hours);
        return cal.getTime();
    }
}
