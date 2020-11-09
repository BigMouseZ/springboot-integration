package com.signalway.base;

import lombok.Data;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.springframework.amqp.core.AcknowledgeMode;

@Data
public class QueueConfg {
    //rabbitmq配置
    //一次获得信息数
    public Integer prefetchCount;
    //消费者数量
    public Integer concurrentConsumers;
    //确认模式
    public AcknowledgeMode acknowledgeMode;

    //rocketmq配置
    public String topic;
    public String tag;
    public Integer messageModel; //1 广播 2集群
    public String consumerGroup;
    public ConsumeFromWhere consumeFromWhere;
    public  Integer consumeMessageBatchMaxSize=1; //默认每次拉取一条
}
