package com.integration.springbootmq.mqlistener;

import com.integration.springbootmq.mq.config.QueueMessageReceiveConfg;
import com.integration.springbootmq.mq.queues.MqQueue;
import com.integration.springbootmq.mq.receive.MqMessagfeReceive;
import com.integration.springbootmq.mq.send.MqMessageSendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 接收视觉轨迹数据
 */
@Service
@Slf4j
public class SubSetVideoTrackDataQueueReceivce extends MqMessagfeReceive<VideoTrackMessage> {
    @Resource
    private MqMessageSendService mqMessageSendService;
    @Value("${uploadSubsetVideoTrack.url:null}")
    String url;
    @Value("${uploadSubsetTrack.url.socketTimeout:30000}")
    int socketTimeout;

    @Override
    public void onMessageData(VideoTrackMessage message) {
        log.debug("队列:" + bindQueue().getQueueName() + "收到数据,线程信息:" + Thread.currentThread().getId() + "--" + Thread.currentThread().getName());
    }

    @Override
    public MqQueue bindQueue() {
        return MqQueue.EXPRESSWAY_SUBSET_VIDEO_TRACK_DATA_QUEUE;
    }

    @Override
    public QueueMessageReceiveConfg receiveConfig() {
        QueueMessageReceiveConfg config = new QueueMessageReceiveConfg();
        config.setConsumersCount(1);
        config.setConsumersMaxPrefetchCount(1);
        return config;
    }

    @Override
    public Class<VideoTrackMessage> messageType() {
        return VideoTrackMessage.class;
    }

}
