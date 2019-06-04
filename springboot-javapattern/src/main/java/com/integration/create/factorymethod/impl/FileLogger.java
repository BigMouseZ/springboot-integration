package com.integration.create.factorymethod.impl;

import com.integration.create.factorymethod.Logger;

/**
 * Created by ZhangGang on 2019/6/4.
 */

//文件日志记录器：具体产品
public class FileLogger implements Logger {
    @Override
    public void writeLog() {
        System.out.println("文件日志记录。");
    }
}
