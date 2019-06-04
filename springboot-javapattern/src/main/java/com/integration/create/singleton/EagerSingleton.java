package com.integration.create.singleton;

/**
 * Created by ZhangGang on 2019/6/4.
 * 饿汉式单例类
 *由于在定义静态变量的时候实例化单例类，因此在类加载的时候就已经创建了单例对象
 *当类被加载时，静态变量instance会被初始化，此时类的私有构造函数会被调用，单例类的唯一实例将被创建。
 * 如果使用饿汉式单例来实现负载均衡器LoadBalancer类的设计，则不会出现创建多个单例对象的情况，可确保单例对象的唯一性。
 */

public class EagerSingleton {
    private static final EagerSingleton instance = new EagerSingleton();
    private EagerSingleton() { }

    public static EagerSingleton getInstance() {
        return instance;
    }
}
