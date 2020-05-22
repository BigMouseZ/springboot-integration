package com.integreation.cms.aop;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by ZhangGang on 2019/7/18.
 *
 * 权限校验
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthCheck {

    //sessionid校验（登录校验）
    boolean checkSessionid() default true;
    //权限校验
    boolean checkAuth() default true;
    //接口数据校验
    boolean datacheck() default false;
    //操作日记记录
    boolean saveLog() default true;
}
