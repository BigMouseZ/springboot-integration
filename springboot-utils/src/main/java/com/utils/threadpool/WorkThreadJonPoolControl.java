package com.utils.threadpool;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ZhangGang on 2018/11/27.
 */
public class WorkThreadJonPoolControl {

    private static Map<String, WorkThreadJonPool> THREADPOOLS=new HashMap<>();
    //创建线程池。在Listener中初始化。
    public static void createNewJobThreadPool(String thread_poolkey,String thread_poolname,Integer threadCount){
        if(!THREADPOOLS.containsKey(thread_poolkey)){
            THREADPOOLS.put(thread_poolkey, new WorkThreadJonPool(threadCount,thread_poolname));
        }
    }
    //执行线程任务，根据线程池名称称
    public static boolean execute_job(String thread_poolkey,Runnable job){
        if(THREADPOOLS.containsKey(thread_poolkey)){
            THREADPOOLS.get(thread_poolkey).execute(job);
        }
        return false;
    }

}
