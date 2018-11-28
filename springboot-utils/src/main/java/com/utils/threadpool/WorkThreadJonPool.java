package com.utils.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by ZhangGang on 2018/11/27.
 */
@Slf4j
public class WorkThreadJonPool{
    //日志
    //线程队列数
    private final int threadcount;
    //LinkedBlockingQueue 基于链表的阻塞队列
    //，LinkedBlockingQueue生产者和消费者都有自己的锁（见下面的代码），这意味着生产者和消费者可以"同时"运行。
    private final LinkedBlockingQueue<Runnable> jobqueue;
    //初始化的线程工作组
    private final JobWorkerTread[] jobWorkers;
    //线程池的名称
    private String poolname;
    //构造函数初始化线程组
    public WorkThreadJonPool(int threadcount,String poolname){
        this.threadcount=threadcount;
        this.poolname=poolname;
        this.jobqueue=  new LinkedBlockingQueue<Runnable>();
        jobWorkers=new JobWorkerTread[this.threadcount];
        for(int i=0;i<threadcount;i++){
            jobWorkers[i]=new JobWorkerTread();
            jobWorkers[i].start();
        }
    }
    //执行线程的添加
    public void execute(Runnable r){
        jobqueue.add(r);
    }
    private  class JobWorkerTread extends Thread{
        public void run(){
            Runnable r;

            while(true){
                try {
                    log.info("线程池_"+poolname+"_任务数量:"+jobqueue.size());
                    //线程队列存在则获取FIFO(先进先出原则)，为空则挂起
                    r=jobqueue.take();
                    log.info("线程池_"+poolname+"执行任务线程ID："+Thread.currentThread().getId());
                    r.run();
                    log.info("线程池_"+poolname+"_执行任务完成");
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }


        }
    }
}
