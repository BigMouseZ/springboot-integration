package com.integration.springboottime.manualtime.timing;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

import java.util.concurrent.CountDownLatch;

/**
 * 定时任务主线程
 * 项目名称：稽查平台基础依赖  
 * 类名称：TimingMainThread  
 * 创建人: lzl
 * 创建时间:2020年4月1日 上午11:32:16  
 * 修改时间:2020年4月1日 上午11:32:16
 */
@Slf4j
public class TimingMainThread implements Runnable{
	private BaseTimingJob timingJob;
	
	public TimingMainThread(BaseTimingJob timingJob){
		this.timingJob = timingJob;
	}
	@Override
	public void run() {
		try{
			boolean fiststTime = true;
			log.warn(String.format("定时任务:%s 周期%s 秒  首次延时执行%s秒", timingJob.jobInformation().getDescribe(),timingJob.getCycleTime(),timingJob.firstDelayedSecond()));
			while(timingJob.isRunState()){
				if(timingJob.firstDelayedSecond() >0 && fiststTime){
					log.warn(String.format("定时任务:%s,首次执行延时执行时间:%s秒", timingJob.jobInformation().getDescribe(),timingJob.firstDelayedSecond()));
					Thread.currentThread().sleep(timingJob.firstDelayedSecond() * 1000);
					fiststTime = false;
				}
				StopWatch stopWatch = new StopWatch();
				stopWatch.start();
				CountDownLatch waitExecute = new CountDownLatch(1);
				Thread jobth = new Thread(new TimingExecuteThread(waitExecute, timingJob));
//				jobth.setName(timingJob.jobInformation().getKey());
				jobth.start();
				waitExecute.await();
				jobth = null;
				stopWatch.stop();
				log.info(String.format("定时任务%s,耗时%s秒", timingJob.jobInformation().getKey(),stopWatch.getTotalTimeSeconds()));
				Thread.currentThread().sleep(timingJob.getCycleTime()*1000);
			}
		}catch(Exception e){
			log.error("定时任务主线程异常",e);
		}
		
	}

}
