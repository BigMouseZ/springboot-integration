package com.utils.quartz.config;

import com.utils.quartz.GpsSyncDeleteQuartzJob;
import com.utils.quartz.GpsSyncQuartzJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ZhangGang on 2018/11/12.
 * 定时任务配置你
 */
@Configuration
public class QuartzConfig {
    @Value("${quartz.GpsSyncTaskCronSchedule}")
    private String GpsSyncTaskCronSchedule;

    @Bean
    public JobDetail GpsSyncTaskDetail() {
        return JobBuilder.newJob(GpsSyncQuartzJob.class).withIdentity("GpsSyncTask").withDescription("同步GPS定时任务").storeDurably().build();
    }

    @Bean
    public CronTrigger GpsSyncTaskTrigger() {
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(GpsSyncTaskCronSchedule);
        return null;
//        return TriggerBuilder.newTrigger().forJob(GpsSyncTaskDetail())
//                .withIdentity("GpsSyncTask").withDescription("同步GPS定时任务").withSchedule(scheduleBuilder).build();
    }

    @Bean
    public JobDetail GpsSyncTaskDetail2() {
        return JobBuilder.newJob(GpsSyncDeleteQuartzJob.class).withIdentity("GpsSyncTask").withDescription("同步GPS定时任务").storeDurably().build();
    }

    @Bean
    public SimpleTrigger GpsSyncTaskTrigger2() {
        // Simple类型：可设置时间间隔、是否重复、触发频率（misfire机制）等
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(5).repeatForever();
        return TriggerBuilder.newTrigger().forJob(GpsSyncTaskDetail2())
                .withIdentity("GpsSyncTask2").withDescription("同步GPS定时任务2").withSchedule(scheduleBuilder).build();
    }
}
