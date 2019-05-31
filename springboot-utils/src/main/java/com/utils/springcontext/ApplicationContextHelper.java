package com.utils.springcontext;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by ZhangGang on 2018/12/3.
 */

@Component
public class ApplicationContextHelper implements ApplicationContextAware {
    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("我已经注入啦");
        context = applicationContext;
    }

    public static <T> T getBean(Class<T> tClass) {
        if (null == context) {
            return null;
        }
        return context.getBean(tClass);
    }
}