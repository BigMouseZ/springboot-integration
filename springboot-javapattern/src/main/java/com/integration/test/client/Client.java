package com.integration.test.client;

import java.util.Calendar;

/**
 * Created by ZhangGang on 2019/6/4.
 */
public class Client {
    public static void main(String args[]) {
        String year = "", month = "", day = "", hour = "", minute = "", second = "", millisecond = "";
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR) + "";
        month = (calendar.get(Calendar.MONTH) + 1) + "";
        day = calendar.get(Calendar.DAY_OF_MONTH) + "";
        hour = calendar.get(Calendar.HOUR_OF_DAY) + "";
        minute = calendar.get(Calendar.MINUTE) + "";
        second = calendar.get(Calendar.SECOND) + "";
        millisecond = calendar.get(Calendar.MILLISECOND) + "";
        System.out.println("year"+year);
        System.out.println("month:"+month);
        System.out.println("day:"+day);
        System.out.println("hour:"+(hour.length() == 1 ? "0" + hour : hour));
        System.out.println("minute:"+(minute.length() == 1 ? "0" + minute : minute));
        System.out.println("second:"+(second.length() == 1 ? "0" + second : second));
        System.out.println("millisecond:"+millisecond);
    }
}

