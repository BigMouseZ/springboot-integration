package com.lombok.at_Log;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by ZhangGang on 2018/11/28.
 *生成log对象，用于记录日志，可以通过topic属性来设置getLogger(String name)方法的参数
 * 例如 @Log4j(topic = “com.xxx.entity.UserService”)，默认是类的全限定名，即 类名.class，log支持以下几种：
 @Log java.util.logging.Logger
 @Log4j org.apache.log4j.Logger
 @Log4j2 org.apache.logging.log4j.Logger
 @Slf4j org.slf4j.Logger
 @XSlf4j org.slf4j.ext.XLogger
 @CommonsLog org.apache.commons.logging.Log
 @JBossLog org.jboss.logging.Logger
 @Log
 private static final java.util.logging.Logger log = java.util.logging.Logger.getLogger(LogExample.class.getName());
 @Log4j
 private static final Logger log = org.apache.log4j.Logger.Logger.getLogger(UserService.class);
 @Log4j2
 private static final org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(LogExample.class);
 @Slf4j
 private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(LogExample.class);
 @XSlf4j
 private static final org.slf4j.ext.XLogger log = org.slf4j.ext.XLoggerFactory.getXLogger(LogExample.class);
 @CommonsLog
 private static final org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(LogExample.class);
 @JBossLog
 private static final org.jboss.logging.Logger log = org.jboss.logging.Logger.getLogger(LogExample.class);
 */
@Slf4j
public class UserService {

    public void addUser(){
        log.info("add user");
    }
}



