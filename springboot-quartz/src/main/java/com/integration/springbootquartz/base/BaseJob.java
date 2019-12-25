package com.integration.springbootquartz.base;

/**
 * Created by ZhangGang on 2019/12/25
 */

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * jobGroup基础接口,自己实现业务逻辑的job类都要实现这个借口
 * 创建任务时要用
 */
public interface BaseJob extends Job {

    public void execute(JobExecutionContext context) throws JobExecutionException;
}
