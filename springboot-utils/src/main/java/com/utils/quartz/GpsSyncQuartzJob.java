package com.utils.quartz;

import com.utils.rabbitmqutils.RabbitMQUtils;
import com.utils.timeutils.TimeUtils;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.io.File;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ZhangGang on 2018/11/12.
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class GpsSyncQuartzJob extends QuartzJobBean {
    private static final Logger logger = LoggerFactory.getLogger(GpsSyncQuartzJob.class);
    @Autowired
    private RabbitMQUtils rabbitMQUtils;
    @Value("${quartz.GpsSyncFilePath}")
    private String GpsSyncFilePath;
    @Value("${quartz.GpsSyncTempFilePath}")
    private String GpsSyncTempFilePath;
    @Value("${quzrtz.sessionid}")
    private String sessionid;
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
            if ("consumer".equals(active)) {
                logger.info("启动扫描GPS数据同步目录-start");
                String pTime = GpsSyncFilePath+ TimeUtils.getCurrentTimeByString(new Date(), "yyyyMMdd")+"\\";
                File fileList = new File(pTime);
                if (fileList.exists()) {
                    File[] childFiles = fileList.listFiles();
                    if (childFiles != null && childFiles.length > 0) {
                        //获取所有文件路径
                        CountDownLatch latch = new CountDownLatch(childFiles.length);//线程计数器
                        ExecutorService executor = Executors.newFixedThreadPool(20);
                        logger.info("启动扫描GPS数据同步目录,文件总量：" + childFiles.length);
                        for (File file : childFiles) {
                           // executor.execute(new SendGpsSnycThread(latch, file, rabbitMQUtils, GpsSyncTempFilePath, sessionid));
                        }
                        executor.shutdown();
                        latch.await();
                    }
                } else {
                    logger.error("GPS数据同步目录不存在" + pTime);
                }
                logger.info("启动扫描GPS数据同步目录-end");
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("扫描GPS数据同步目录异常" + e);
        }
    }
}
