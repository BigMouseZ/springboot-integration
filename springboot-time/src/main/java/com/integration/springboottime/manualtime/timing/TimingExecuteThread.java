package com.integration.springboottime.manualtime.timing;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

/**
 * 定时任务子线程
 * 具体执行任务的线程
 * 项目名称：稽查平台基础依赖  
 * 类名称：TimingExecuteThread  
 * 创建人: lzl
 * 创建时间:2020年4月1日 上午11:53:08  
 * 修改时间:2020年4月1日 上午11:53:08
 */
@Slf4j
public class TimingExecuteThread implements Runnable{
	private CountDownLatch waiteRun;
	private BaseTimingJob timingJob;
	
	public TimingExecuteThread(CountDownLatch waiteRun, BaseTimingJob timingJob) {
		super();
		this.waiteRun = waiteRun;
		this.timingJob = timingJob;
	}

	@Override
	public void run() {
		try{
			timingJob.executeJob();
		}catch(Exception e){
			log.error("线程执行异常：",e);
		}finally{
			waiteRun.countDown();
		}
		
	}

}
