package com.utils.threadpool.demo;


import com.utils.threadpool.WorkThreadJonPoolControl;
import com.utils.threadpool.config.ThreadConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by ZhangGang on 2018/3/13.
 */
@Slf4j
public class ThreadPoolDemoQuartz {
    @Autowired
    private ThreadPoolDemoService threadPoolDemoService;
    private void parseCarDataAndUploadFtp( List list) {
        List<String> pathList = new ArrayList<String>();
        final CountDownLatch jobCount = new CountDownLatch(list.size());
        log.info("总共有" + list.size() + "条数据需要生成xml");
        for(int i = 0;i<list.size();i++){

             list.get(i);
            //线程池处理
            WorkThreadJonPoolControl.execute_job(ThreadConfig.carDataThreadPopolKey,new ThreadPoolDemoJob(jobCount,pathList,threadPoolDemoService));
        }
        try {
            jobCount.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
            log.error("[error][cardata]: "+ e);
        }
    }
}
