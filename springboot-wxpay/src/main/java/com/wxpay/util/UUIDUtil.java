package com.wxpay.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.UUID;

/**
 * Created by Chenzq on 2018/3/5.
 */
public class UUIDUtil {

    static Random random;

    static {
        random = new Random();
    }

    public static String newOne() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String newNo(String prefix) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssS" + random.nextInt(9999999));
        return prefix + sdf.format(calendar.getTime());
    }
}
