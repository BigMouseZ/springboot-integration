package com.integration.create.factorymethod.impl;

import com.integration.create.factorymethod.Logger;
import com.integration.create.factorymethod.LoggerFactory;

/**
 * Created by ZhangGang on 2019/6/4.
 */
//数据库日志记录器工厂类：具体工厂
public class DatabaseLoggerFactory implements LoggerFactory {
    public Logger createLogger() {
        //连接数据库，代码省略
        //创建数据库日志记录器对象
        Logger logger = new DatabaseLogger();
        //初始化数据库日志记录器，代码省略
        return logger;
    }

}
