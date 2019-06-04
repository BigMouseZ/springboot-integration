package com.integration.create.factorymethod.impl;

import com.integration.create.factorymethod.Logger;

/**
 * Created by ZhangGang on 2019/6/4.
 */
public class DatabaseLogger implements Logger {
    @Override
    public void writeLog() {
        System.out.println("数据库日志记录。");
    }

}
