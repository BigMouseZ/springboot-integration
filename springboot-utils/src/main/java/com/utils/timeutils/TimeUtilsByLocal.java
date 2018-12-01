package com.utils.timeutils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by ZhangGang on 2018/12/1.
 */
public class TimeUtilsByLocal {
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

    /**
    * 获取当前时间
    *@return 返回"yyyy-MM-dd HH:mm:ss"
    * */
    public static String getCurrentTimeString() {
        String localDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern(format));
        //String date = localDateTime.format(DateTimeFormatter.ofPattern(format));
        System.out.println(localDateTime);
        System.out.println(localDateTime);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }


    public static void main(String[] args) {
        getCurrentTimeString();
    }
}
