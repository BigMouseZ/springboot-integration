package com.utils.quartz;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Created by ZhangGang on 2018/11/12.
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class GpsSyncDeleteQuartzJob extends QuartzJobBean {
    private static final Logger logger = LoggerFactory.getLogger(GpsSyncDeleteQuartzJob.class);
    @Value("${quartz.GpsSyncFilePath}")
    private String GpsSyncFilePath;
    @Value("${quartz.GpsSyncTempFilePath}")
    private String GpsSyncTempFilePath;

    @Value("${quartz.deletedate}")
    private int deletedate;
    @Value("${spring.profiles.active}")
    private String active;

    /**
     * Execute the actual job. The job data map will already have been
     * applied as bean property values by execute. The contract is
     * exactly the same as for the standard Quartz execute method.
     *
     * @param context
     * @see #execute
     */
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

        try {
             /*如果是消费者的配置，才执行数据删除*/
            if ("consumer".equals(active)) {
                logger.info("启动GPS数据删除任务-start");
                int number = 0;//FileUtils.deleteFileDate(GpsSyncTempFilePath, -deletedate);
                int filenumber = 0;// FileUtils.deleteFileDate(GpsSyncFilePath,-deletedate);
                logger.info("成功数据删除文件：" + number + "条");
                logger.info("成功数据删除文件夹：" + filenumber + "个");
                logger.info("启动GPS数据删除任务-end");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("GPS数据删除异常" + e);
        }
    }
}
