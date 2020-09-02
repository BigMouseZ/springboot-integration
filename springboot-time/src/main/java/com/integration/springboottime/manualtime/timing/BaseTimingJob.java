package com.integration.springboottime.manualtime.timing;

import lombok.extern.slf4j.Slf4j;

/**
 * 定时任务触发基础类
 * 项目名称：稽查平台基础依赖  
 * 类名称：BaseTimingJob  
 * 创建人: lzl
 * 创建时间:2020年4月1日 上午10:44:01  
 * 修改时间:2020年4月1日 上午10:44:01
 */
@Slf4j
public abstract class BaseTimingJob {
	
	private boolean runState = true;
	
	private long cycleTime = 60;
	
	
	public boolean isRunState() {
		return runState;
	}

	public void setRunState(boolean runState) {
		this.runState = runState;
	}

	public long getCycleTime() {
		cycleTime = cycleSecond();
		return cycleTime;
	}

	public void setCycleTime(long cycleTime) {
		this.cycleTime = cycleTime;
	}

	public BaseTimingJob(){
		//将定时任务注册到管理器
		if(jobInformation() == null){
			log.error(String.format("定时任务作业未配置基础信息,类名%s", this.getClass().getName()));
		}else{
			if(cycleSecond() >= 0){
				//注册到管理器
				TimingJobManager.registerTimingJob(jobInformation(), this);
				log.info(String.format("已完成定时任务注册,实现类:%s", this.getClass().getName()));
			}else{
				log.error(String.format("定时任务%s,设置的周期小于等于0无法装载此定时任务,描述:%s", jobInformation().getKey(),jobInformation().getDescribe()));
			}
		}
	}
	
	/**
	 * 执行具体任务
	 */
	public abstract void executeJob();
	
	/**
	 * 设置定时任务基本信息
	 * @return
	 */
	public abstract TimingJobInformation jobInformation();
	/**
	 * 首次执行延时执行设置
	 * 可以设置首次启动时延时多久执行任务
	 * 
	 * 说明：
	 * 为了达到特定的时间点执行指定任务，可按如下规则处理
	 * 比如：指定每天晚上02点执行某个任务
	 * 此时可设置首次时间未当前时间到此时间距离的秒数
	 * 周期设置未24小时X60分钟X60秒
	 * 如此设置就可以达到每天指定时间点执行某个任务
	 * @return
	 */
	public abstract long firstDelayedSecond();
	
	/**
	 * 周期单位秒
	 * @return
	 */
	public abstract long cycleSecond();
	
	

}
