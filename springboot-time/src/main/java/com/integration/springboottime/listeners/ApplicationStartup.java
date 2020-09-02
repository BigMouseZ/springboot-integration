package com.integration.springboottime.listeners;

import com.integration.springboottime.manualtime.timing.TimingJobManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 *
 * 项目名称：稽查平台数据清洗稽查
 * 类名称：ApplicationStartup
 * 创建人: lzl
 * 创建时间:2020年6月27日 下午2:56:58
 * 修改时间:2020年6月27日 下午2:56:58
 */
@Slf4j
@Component
public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        log.info("系统参数初始化中....");
        try {

            //启动定时任务
            TimingJobManager.startTimingJob();
            log.info("系统参数初始化完毕....");
        } catch (Exception e) {
            log.error("系统参数初始化异常：", e);
        }
        stopWatch.stop();
        log.info("系统参数初始化完毕!耗时：{}", stopWatch.getTotalTimeSeconds());
    }



}

