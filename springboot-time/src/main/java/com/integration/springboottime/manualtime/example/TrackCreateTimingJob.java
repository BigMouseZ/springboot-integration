package com.integration.springboottime.manualtime.example;

import com.integration.springboottime.manualtime.timing.BaseTimingJob;
import com.integration.springboottime.manualtime.timing.TimingJobInformation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 发送图像通行数据定时任务
 * 项目名称：稽查平台定时任务集成
 * 类名称：TrackCreateJob
 * 创建人: lzl
 * 创建时间:2020年4月1日 下午1:58:48
 * 修改时间:2020年4月1日 下午1:58:48
 */
@Service
@Slf4j
public class TrackCreateTimingJob extends BaseTimingJob {
    /**
     * 单次发送图像通行数据的数据量
     */
    @Value("${timing.trackVideo.firstDelayedSecond:10}")
    long firstDelayedSecond;
    @Value("${timing.trackVideo.cycleSecond:30}")
    long cycleSecond;

    @Override
    public void executeJob() {

        log.warn("开始提取视觉轨迹还原所需要的数据,周期" + cycleSecond + "首次等待" + firstDelayedSecond);
        log.info(new Date().toString());

    }

    @Override
    public TimingJobInformation jobInformation() {
        return TimingJobInformation.TIMING_TRACK_CREATE_DATA_SEND;
    }

    @Override
    public long firstDelayedSecond() {
        return firstDelayedSecond;
    }

    @Override
    public long cycleSecond() {
        return cycleSecond;
    }

}
