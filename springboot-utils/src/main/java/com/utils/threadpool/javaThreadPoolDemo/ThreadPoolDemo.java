package com.utils.threadpool.javaThreadPoolDemo;

import lombok.SneakyThrows;

/**
 * Created by ZhangGang on 2018/11/28.
 */
public class ThreadPoolDemo {

    @SneakyThrows
    public void demo(){
            //获取所有文件路径
          /*  CountDownLatch latch = new CountDownLatch(filePaths.length);//线程计数器
            List<String> tempTableNameList = new ArrayList<>();
            ExecutorService executor = Executors.newFixedThreadPool(20);//4个线程，4个线程以上无区别，疑惑
            for(File filepath:filePaths){
                executor.execute(new Image(imageMatchService,latch,filepath,tempTableNameList));
            }
            latch.await();
            executor.shutdown();*/
    }

}
