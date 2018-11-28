package com.utils.threadpool.demo;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by ZhangGang on 2018/3/13.
 */
public class ThreadPoolDemoJob implements Runnable{

    private Logger logger = LoggerFactory.getLogger(ThreadPoolDemoJob.class);
    private CountDownLatch jobCount;
    private List<String> pathList;
    private ThreadPoolDemoService threadPoolDemoService;

    public ThreadPoolDemoJob(CountDownLatch jobCount, List<String> pathList, ThreadPoolDemoService threadPoolDemoService) {
        this.jobCount = jobCount;
        this.pathList = pathList;
        this.threadPoolDemoService = threadPoolDemoService;
    }
    @Override
    public void run() {

        try{
            String path = threadPoolDemoService.demo();
            if(StringUtils.isNotBlank(path)){
                synchronized (pathList){
                    pathList.add(path);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error("[error][cardata]: "+ e);
        }finally {
            jobCount.countDown();
        }
    }
}

