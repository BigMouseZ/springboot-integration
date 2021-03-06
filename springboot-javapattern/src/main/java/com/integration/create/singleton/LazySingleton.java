package com.integration.create.singleton;

/**
 * Created by ZhangGang on 2019/6/4.
 *
 * 懒汉式单例类与线程锁定
 * 双重检查锁定(Double-Check Locking)
 */
public class LazySingleton {
    private volatile static LazySingleton instance = null;

    private LazySingleton() { }

    public static LazySingleton getInstance() {
        //第一重判断
        if (instance == null) {
            //锁定代码块
            synchronized (LazySingleton.class) {
                //第二重判断
                if (instance == null) {
                    instance = new LazySingleton(); //创建单例实例
                }
            }
        }
        return instance;
    }
}
