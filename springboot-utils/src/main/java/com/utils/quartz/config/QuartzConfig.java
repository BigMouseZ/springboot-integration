package com.utils.quartz.config;

import com.utils.quartz.GpsSyncDeleteQuartzJob;
import com.utils.quartz.GpsSyncQuartzJob;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
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
    @Value("${quartz.GpsSyncDeleteTaskCronSchedule}")
    private String GpsSyncDeleteTaskCronSchedule;

    @Bean
    public JobDetail GpsSyncTaskDetail() {
        return JobBuilder.newJob(GpsSyncQuartzJob.class).withIdentity("GpsSyncTask").withDescription("同步GPS定时任务").storeDurably().build();
    }
    @Bean
    public Trigger GpsSyncTaskTrigger() {
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(GpsSyncTaskCronSchedule);
        return TriggerBuilder.newTrigger().forJob(GpsSyncTaskDetail())
                .withIdentity("GpsSyncTask").withDescription("同步GPS定时任务").withSchedule(scheduleBuilder).build();
    }
    @Bean
    public JobDetail GpsSyncDeleteTaskDetail() {
        return JobBuilder.newJob(GpsSyncDeleteQuartzJob.class).withIdentity("GpsSyncDeleteTask").withDescription("GPS数据删除定时任务").storeDurably().build();
    }
    @Bean
    public Trigger GpsSyncDeleteTaskTrigger() {
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(GpsSyncDeleteTaskCronSchedule);
        return TriggerBuilder.newTrigger().forJob(GpsSyncDeleteTaskDetail())
                .withIdentity("GpsSyncDeleteTask").withDescription("GPS数据删除定时任务").withSchedule(scheduleBuilder).build();
    }
}
