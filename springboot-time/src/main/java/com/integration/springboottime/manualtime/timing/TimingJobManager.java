package com.integration.springboottime.manualtime.timing;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class TimingJobManager {
	
	private static Map<String,BaseTimingJob> TIMINGJOB_MAP = new HashMap<String ,BaseTimingJob>();
	private static Map<String, Thread> TIMINGjOB_THREAD_MAP = new HashMap<String, Thread>();
	
	public static void registerTimingJob(TimingJobInformation info ,BaseTimingJob job){
		if(info != null && job != null){
			log.warn(String.format("已完完成定时任务注册:key=%s,任务描述:%s", info.getKey(),info.getDescribe()));
			TIMINGJOB_MAP.put(info.getKey(), job);
		}
	}
	
	public static void startTimingJob(){
		if(TIMINGJOB_MAP.size() == 0){
			log.warn("未定义任务定时任务作业");
			return;
		}
		for(BaseTimingJob jobservice:TIMINGJOB_MAP.values()){
			if(!TIMINGjOB_THREAD_MAP.containsKey(jobservice.jobInformation().getKey())){
				Thread mainJobTh = new Thread(new TimingMainThread(jobservice));
				mainJobTh.setName("MAINTHREAD_"+jobservice.jobInformation().getKey());
				mainJobTh.start();
				log.warn(jobservice.jobInformation().getKey()+"定时任务线程启动完成"+",周期:"+jobservice.cycleSecond()+"秒"+",首次等待:"+jobservice.firstDelayedSecond()+"秒");
				TIMINGjOB_THREAD_MAP.put(jobservice.jobInformation().getKey(), mainJobTh);
			}else{
				log.error("定时任务主线程已在运行，不可重复启动,"+jobservice.jobInformation().getKey());
			}
			
		}
		
	}

}
