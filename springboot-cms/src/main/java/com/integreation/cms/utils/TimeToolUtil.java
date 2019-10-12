package com.integreation.cms.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class TimeToolUtil {

    public final static String format = "yyyy-MM-dd HH:mm:ss";

    public static Date StringToDate(String time) {
        if (time != null) {
            time = time.replaceAll("/", "-");
            try {
                return new SimpleDateFormat(format).parse(time);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String getCurrentTimeByString(Date date, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(date);
    }

    public static String getCurrentTimeString() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    public static Date addDay(Date dateFrom, int dayNum) {
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Date beginDate = new Date();
        Calendar date = Calendar.getInstance();
        date.setTime(dateFrom);
        date.set(Calendar.DATE, date.get(Calendar.DATE) + dayNum);
        Date endDate = null;
        try {
            endDate = dft.parse(dft.format(date.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return endDate;
    }

    public static Date addDate(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    public static Date addHours(Date date, int hours) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR_OF_DAY, hours);
        return cal.getTime();
    }


    /**
     * 追加时间的时分秒
     *
     * @param date
     * @param startorend ture 起始 false 结束
     * @return
     */
    public static Date getDateAdd_hms(Date date, boolean startorend) {

        if (date != null) {
            try {
                String datestr = new SimpleDateFormat("yyyy-MM-dd").format(date);
                if (startorend) {
                    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(datestr + " 00:00:00");
                } else {
                    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(datestr + " 23:59:59");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public static String getDateAdd_hms(String dateString,boolean startorend) {
        if (StringUtils.isNotBlank(dateString)) {
            try {
                if(startorend){
                    String datestr = new SimpleDateFormat("yyyy-MM-dd").format(StringToDate(dateString));
                    Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(datestr + " 00:00:00");
                    return TimeToolUtil.getCurrentTimeByString(date, TimeToolUtil.format);
                }else {
                    Date dateend =  addDate(StringToDate(dateString),1);
                    String datestr = new SimpleDateFormat("yyyy-MM-dd").format(dateend);
                    Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(datestr + " 00:00:00");
                    return TimeToolUtil.getCurrentTimeByString(date, TimeToolUtil.format);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    /**
     * 效验时间格式是否正确
     * 适用格式：yyyy-MM-dd HH:MM:ss和yyyy-MM-dd
     *
     * @param datetime
     * @return
     */
    public static boolean checkDateFormat(String datetime) {
        if (datetime != null) {
            return datetime.matches("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1][0-9])|([2][0-4]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
        }
        return false;
    }


    public static void main(String[] args) {
        float f = 20f;
        long l = (long) f;

    }

    public static Map<String, Integer> getYearMonthDayHourFromDateStr(String dateStr) {
        Map<String, Integer> map = new HashMap<>();
        map.put("year", Integer.parseInt(dateStr.substring(0, dateStr.indexOf("-"))));
        map.put("month", Integer.parseInt(dateStr.substring(dateStr.indexOf("-") + 1, dateStr.indexOf("-") + 3)));
        map.put("day", Integer.parseInt(dateStr.substring(dateStr.indexOf(" ") - 2, dateStr.indexOf(" "))));
        map.put("hour", Integer.parseInt(dateStr.substring(dateStr.indexOf(" ") + 1, dateStr.indexOf(" ") + 3)));
        return map;
    }


    public static Date getDateByString(String str, String formatStr) {
        SimpleDateFormat format = new SimpleDateFormat(formatStr/*"yyyy-MM-dd HH:mm:ss"*/);
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    //获取当天的开始时间
    public static Date getDayBegin() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    //获取下一天的开始时间
    public static Date getTomorrowBegin() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    //获取当天的结束时间
    public static Date getDayEnd() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }

    public static Date addSecond(Date date, int second) {
        if (date != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(Calendar.SECOND, second);
            date = c.getTime();
            return date;
        }
        return null;
    }

    public static Date addMinute(Date date, int minute) {
        if (date != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(Calendar.MINUTE, minute);
            date = c.getTime();
            return date;
        }
        return null;
    }

    public static Date moveByMinute(Date targetdate, Integer minute) {
        if (targetdate != null && minute != null && minute != 0) {
            Long time = targetdate.getTime();
            time = (time + (minute * 60 * 1000));
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(time);

            return calendar.getTime();
        }

        return targetdate;
    }


    public static Date longToDate(Long date) {
        if (date != null) {
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(date);
            return c.getTime();
        }
        return null;
    }


}
