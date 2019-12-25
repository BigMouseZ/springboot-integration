package com.integration.springbootquartz.job;

import com.integration.springbootquartz.base.BaseJob;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.stereotype.Component;

/**
 * Created by ZhangGang on 2019/12/25
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
@Component
public class JobTwo implements BaseJob {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap data=context.getTrigger().getJobDataMap();
        Integer invokeParam = (Integer) data.get("invokeParam");
        //在这里实现业务逻辑
        System.out.println("JobTwo开始运行,任务参数是:" + invokeParam);
    }
}
