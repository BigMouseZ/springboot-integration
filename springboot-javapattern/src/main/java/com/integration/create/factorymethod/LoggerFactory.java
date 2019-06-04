package com.integration.create.factorymethod;

/**
 * Created by ZhangGang on 2019/6/4.
 */

//日志记录器工厂接口：抽象工厂
public interface LoggerFactory {
    public Logger createLogger();
}
