package com.integration.create.factorymethod.impl;

import com.integration.create.factorymethod.Logger;
import com.integration.create.factorymethod.LoggerFactory;

/**
 * Created by ZhangGang on 2019/6/4.
 */

//文件日志记录器工厂类：具体工厂
public class FileLoggerFactory implements LoggerFactory {
    public Logger createLogger() {
        //创建文件日志记录器对象
        Logger logger = new FileLogger();
        //创建文件，代码省略
        return logger;
    }
}
