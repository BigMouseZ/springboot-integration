package com.integration.springbootquartz.entity;

/**
 * Created by ZhangGang on 2019/12/25
 */
public class AppQuartz {

    private Integer quartzId;       //id  主键
    private String jobName;         //任务名称
    private String jobClassname;    //执行任务逻辑那个类名
    private String jobGroup;        //任务分组
    private String jobType;         //任务类型
    private Integer timeType;       //时间类型
    private String cronExpression;  //corn表达式
    private Integer invokeParam;//需要传递的参数

    public AppQuartz() {
    }

    public AppQuartz(Integer quartzId, String jobName, String jobClassname, String jobGroup, String jobType, Integer timeType, String cronExpression, Integer invokeParam) {
        this.quartzId = quartzId;
        this.jobName = jobName;
        this.jobClassname = jobClassname;
        this.jobGroup = jobGroup;
        this.jobType = jobType;
        this.timeType = timeType;
        this.cronExpression = cronExpression;
        this.invokeParam = invokeParam;
    }

    //后面要用到
    public String getJobClassname() {
        return "com.example.demo_quartz.job_group." + jobClassname;
    }

    public void setJobClassname(String jobClassname) {
        this.jobClassname = jobClassname;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public Integer getTimeType() {
        return timeType;
    }

    public void setTimeType(Integer timeType) {
        this.timeType = timeType;
    }

    public Integer getQuartzId() {
        return quartzId;
    }

    public void setQuartzId(Integer quartzId) {
        this.quartzId = quartzId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public Integer getInvokeParam() {
        return invokeParam;
    }

    public void setInvokeParam(Integer invokeParam) {
        this.invokeParam = invokeParam;
    }

    @Override
    public String toString() {
        return "AppQuartz{" +
                "quartzId=" + quartzId +
                ", jobName='" + jobName + '\'' +
                ", jobClassname='" + jobClassname + '\'' +
                ", jobGroup='" + jobGroup + '\'' +
                ", jobType='" + jobType + '\'' +
                ", timeType=" + timeType +
                ", cronExpression='" + cronExpression + '\'' +
                ", invokeParam=" + invokeParam +
                '}';
    }
}
