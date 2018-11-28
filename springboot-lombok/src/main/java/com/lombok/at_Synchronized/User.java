package com.lombok.at_Synchronized;

import lombok.Synchronized;

/**
 * Created by ZhangGang on 2018/11/28.
 *给方法加上同步锁
 */
public class User {
    private final Object readLock = new Object();

    @Synchronized
    public static void foo(){
        System.out.println();
    }

    @Synchronized
    public void bar(){
        System.out.println();
    }

    @Synchronized("readLock")
    public void test() {
        System.out.println();
    }
}

