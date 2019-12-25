package com.integration.springbootquartz.service;

/**
 * Created by ZhangGang on 2019/12/25
 */

import com.integration.springbootquartz.base.BaseJob;
import com.integration.springbootquartz.entity.AppQuartz;
import com.integration.springbootquartz.util.DateUnit;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import static org.quartz.DateBuilder.futureDate;

@Service
public class QuartzService {


    @Autowired
    @Qualifier("Scheduler")
    private Scheduler scheduler;

    @Autowired
    private DateUnit dateUnit;

    /**
     * 新建一个任务
     *
     */
    public String addJob(AppQuartz appQuartz) throws Exception  {
        if ("".equals(appQuartz.getJobGroup()) || "".equals(appQuartz.getJobName()) || "".equals(appQuartz.getJobClassname()) || "".equals(appQuartz.getCronExpression())) {
            return "没有接收到参数,请检查";
        }
        if (appQuartz.getTimeType() == null) {
            return addCronJob(appQuartz);
        }
        return addSimpleJob(appQuartz);
    }

    //CronTrigger
    public String addCronJob(AppQuartz appQuartz) throws Exception {

        // 启动调度器
        scheduler.start();

        //构建job信息
        JobDetail jobDetail = JobBuilder.newJob(getClass(appQuartz.getJobClassname().trim()).getClass()).
                withIdentity(appQuartz.getJobClassname(), appQuartz.getJobGroup())
                .build();

        //表达式调度构建器(即任务执行的时间)
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(appQuartz.getCronExpression());
        //按新的cronExpression表达式构建一个新的trigger
        CronTrigger trigger = TriggerBuilder.newTrigger().
                withIdentity(appQuartz.getJobClassname(), appQuartz.getJobGroup())
                .withSchedule(scheduleBuilder)
                .build();

        //传递参数
        if(appQuartz.getInvokeParam()!=null && !"".equals(appQuartz.getInvokeParam())) {
            trigger.getJobDataMap().put("invokeParam",appQuartz.getInvokeParam());
        }
        try {
            scheduler.scheduleJob(jobDetail, trigger);
            return "success";
        } catch (SchedulerException e) {
            System.out.println("创建定时任务失败" + e);
            return e.getMessage();
        }
    }

    //Simple Trigger
    public String addSimpleJob(AppQuartz appQuartz) throws Exception {
        // 启动调度器
        scheduler.start();

        //构建job信息
        JobDetail jobDetail = JobBuilder.newJob(getClass(appQuartz.getJobClassname().trim()).getClass())
                .withIdentity(appQuartz.getJobGroup(), appQuartz.getJobName())
                .build();

        DateBuilder.IntervalUnit verDate = dateUnit.verification(appQuartz.getTimeType());
        SimpleTrigger simpleTrigger = (SimpleTrigger) TriggerBuilder.newTrigger()
                .withIdentity(appQuartz.getJobClassname(), appQuartz.getJobGroup())
                .startAt(futureDate(Integer.parseInt(appQuartz.getCronExpression()), verDate))
                .forJob(appQuartz.getJobClassname(), appQuartz.getJobGroup())
                .build();

        //传递参数
        if(appQuartz.getInvokeParam()!=null && !"".equals(appQuartz.getInvokeParam())) {
            simpleTrigger.getJobDataMap().put("invokeParam",appQuartz.getInvokeParam());
        }
        try {
            scheduler.scheduleJob(jobDetail, simpleTrigger);
            return "success";
        } catch (SchedulerException e) {
            System.out.println("创建定时任务失败" + e);
            return e.getMessage();
        }
    }

    /**
     * 暂停job
     */
    public String pausejob(Integer[] quartzIds) throws Exception {
        AppQuartz appQuartz=null;
        if(quartzIds.length>0){
            for(Integer quartzId:quartzIds) {
                //这里要先查询出任务信息,暂停需要任务名字和组名
                jobPause(appQuartz.getJobClassname(), appQuartz.getJobGroup());
            }
            return "success";
        }else {
            return "fail pauseJob";
        }
    }

    public void jobPause(String jobClassName, String jobGroupName) throws Exception {
        scheduler.pauseJob(JobKey.jobKey(jobClassName, jobGroupName));
    }


    /**
     * 恢复job
     * @param quartzIds
     * @return
     */
    public String resumejob(Integer[] quartzIds) throws SchedulerException {
        AppQuartz appQuartz=null;
        if(quartzIds.length>0){
            for(Integer quartzId:quartzIds) {
                //这里要先查询出任务信息,暂停需要任务名字和组名
                jobResume(appQuartz.getJobClassname(), appQuartz.getJobGroup());
            }
            return "success";
        }else {
            return "fail pauseJob";
        }
    }
    public void jobResume(String jobClassName, String jobGroupName) throws SchedulerException {
        scheduler.pauseJob(JobKey.jobKey(jobClassName, jobGroupName));
    }


    /**
     * 删除job
     * @param quartzIds
     * @return
     * @throws Exception
     */
    public String deletjob(Integer[] quartzIds) throws Exception {
        AppQuartz appQuartz=null;
        if(quartzIds.length>0){
            for(Integer quartzId:quartzIds) {
                //这里要先查询出任务信息,暂停需要任务名字和组名
                jobDelete(appQuartz.getJobClassname(), appQuartz.getJobGroup());
            }
            return "success";
        }else {
            return "fail pauseJob";
        }
    }
    public void jobDelete(String jobClassName, String jobGroupName) throws Exception {
        scheduler.pauseTrigger(TriggerKey.triggerKey(jobClassName, jobGroupName));
        scheduler.unscheduleJob(TriggerKey.triggerKey(jobClassName, jobGroupName));
        scheduler.deleteJob(JobKey.jobKey(jobClassName, jobGroupName));
    }


    /**
     * 修改任务
     * @param appQuartz
     * @return
     */
    public String modifyJob(AppQuartz appQuartz) throws Exception {
        jobreschedule(appQuartz.getJobClassname(), appQuartz.getJobGroup(), appQuartz.getCronExpression());
        return "success";
    }
    public void jobreschedule(String jobClassName, String jobGroupName, String cronExpression) throws Exception {
        TriggerKey triggerKey = TriggerKey.triggerKey(jobClassName, jobGroupName);
        // 表达式调度构建器
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

        // 按新的cronExpression表达式重新构建trigger
        trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

        // 按新的trigger重新设置job执行
        scheduler.rescheduleJob(triggerKey, trigger);

    }


    /**
     * 暂停所有任务
     * @return
     * @throws SchedulerException
     */
    public String pauseAllJob() throws SchedulerException {
        scheduler.pauseAll();
        return "success";
    }

    /**
     * 恢复所有任务
     * @return
     * @throws SchedulerException
     */
    public String resumeAllJob() throws SchedulerException {
        scheduler.resumeAll();
        return "success";
    }

    /**
     * 根据类名称，通过反射得到该类，然后创建一个BaseJob的实例。
     * 由于自己的Job类都实现了BaseJob，
     * 所以这里不需要我们手动去判断。这里涉及到了一些java多态调用的机制
     *
     * @param classname
     * @return
     * @throws Exception
     */
    public static BaseJob getClass(String classname) throws Exception {
        Class<?> class1 = Class.forName(classname);
        return (BaseJob) class1.newInstance();
    }

    /**
     * 查询任务
     * 这里因为自己建了任务信息表,就直接查自己的表就可以啦
     * 不然的话要连表查询quartz自带的好几张表
     */
}

