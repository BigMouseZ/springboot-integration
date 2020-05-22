package com.integration.create.factorymethod.client;

import com.integration.create.factorymethod.Logger;
import com.integration.create.factorymethod.LoggerFactory;
import com.integration.create.factorymethod.impl.FileLoggerFactory;

/**
 * Created by ZhangGang on 2019/6/4.
 */
public class Client {
    public static void main(String args[]) {
        LoggerFactory factory;
        Logger logger;
        factory = new FileLoggerFactory(); //可引入配置文件实现
        logger = factory.createLogger();
        logger.writeLog();
    }
}
