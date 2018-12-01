package com.utils.timeutils;

import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtils {

    /**
     * 传入一个date对象（无所谓是否带时分秒）后获取一天中的最后时刻，并返回"yyyy-MM-dd HH:mm:ss"格式字符串<p>
     * 一般用于传入的“格式为yyyy-MM-dd 00:00:00的结束时间”的调整
     *
     * @param date
     * @return
     */
    public static String theLastTimeOfDay(Date date) {
        try {
            String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(date);
            date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
            String theLastTimeOfDay = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(DateUtils.addMilliseconds(DateUtils.addDays(date, 1), -1));
            return theLastTimeOfDay;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    //	private  static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	private  static SimpleDateFormat formatter_day = new SimpleDateFormat("yyyy-MM-dd");
    public final static String format = "yyyy-MM-dd HH:mm:ss";

    public static String getCurrentTimeByString(Date date, String format) {
        if (date != null) {
            SimpleDateFormat formatter = new SimpleDateFormat(format);
            return formatter.format(date);
        } else {

            return null;
        }

    }

    public static String getCurrentTimeString() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }


    /*string类型转换为date类型
    *
    * yyyy-MM-dd HH:mm:ss
    * */
    public static Date stringToDate(String strTime)
            throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        date = formatter.parse(strTime);
        return date;
    }
    /*string类型转换为date类型
   * */
    public static Date stringToDateFormat(String strTime,String format)
            throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        Date date = null;
        date = formatter.parse(strTime);
        return date;
    }

    /**
     * 获取当前系统时间 Date 类型
     * yyyy-MM-dd HH:mm:ss
     */
    public static Date getCurrentDateTime() throws ParseException {

        String strTime = getCurrentTimeString();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        date = formatter.parse(strTime);
        return date;
    }


    /*获取两个时间差，返回秒*/
    public static long getDateDiff(Date endDate, Date nowDate) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少秒//输出结果
        long sec = diff / ns;
        return sec;
    }
    /**
    *时间小时的加减
     *
     *
     * */
    public static Date addHour(Date dateOper,int hour){
        try{
            Calendar cal= Calendar.getInstance();
            cal.setTime(dateOper);
            cal.add(Calendar.HOUR_OF_DAY,hour);
            return  cal.getTime();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return  null;
    }

}
