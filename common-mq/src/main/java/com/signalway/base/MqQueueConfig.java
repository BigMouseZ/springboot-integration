package com.signalway.base;

import lombok.Data;

@Data
public class MqQueueConfig {
    //rabbitmq 配置
    private String queueName;
    private String queuedescribe;
    private String routkey;

    //rocketmq配置
}
