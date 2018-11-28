package com.lombok.at_SneakyThrows;

import lombok.SneakyThrows;

/**
 * Created by ZhangGang on 2018/11/28.
 *使用try catch 来捕获异常, 默认捕获的是Throwable异常，也可以设置要捕获的异常
 */
public class User {

    @SneakyThrows
    public void sleep(){
        Thread.sleep(1000);
    }

    @SneakyThrows(InterruptedException.class)
    public void sleep2()  {
        Thread.sleep(1000);
    }
}

