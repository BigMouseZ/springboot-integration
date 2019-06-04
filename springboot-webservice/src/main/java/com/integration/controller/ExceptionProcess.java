package com.integration.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;

/**
 * Created by ZhangGang on 2019/6/4.
 */
@ControllerAdvice
public class ExceptionProcess {
    // 对这个异常的统一处理，返回值 和Controller的返回规则一样
    @ExceptionHandler(MultipartException.class)
    public String handleAll(Throwable t){
        t.printStackTrace();
        // TODO do sth
        return "UploadResult: Result=1";
    }
}