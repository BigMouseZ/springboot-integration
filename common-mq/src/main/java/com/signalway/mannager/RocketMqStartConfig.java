package com.signalway.mannager;

import com.signalway.base.MqQueueConfig;
import com.signalway.recive.RocketMqListener;
import com.signalway.utils.UuidCreate;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Component
public class RocketMqStartConfig implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

       // List<MqQueueConfig> queues = MqManager.allqueue();
        List<RocketMqListener> queues = MqManager.allRocketMqListener();
        try {
            if (queues.size() > 0) {
                for (RocketMqListener rocketMqListener : queues) {
                    DefaultMQPushConsumer consumer = new DefaultMQPushConsumer();
                    rocketMQTemplate.getProducer().getNamesrvAddr();
                    consumer.setNamesrvAddr(  rocketMQTemplate.getProducer().getNamesrvAddr() );
                    consumer.setNamespace(  rocketMQTemplate.getProducer().getNamespace() );
                   // consumer.setInstanceName(  );
                    //消费模式 广播还是 集群
                    if(rocketMqListener.receiveConfig().getMessageModel()!=null&&rocketMqListener.receiveConfig().getMessageModel().intValue()==1) {
                        consumer.setMessageModel(MessageModel.BROADCASTING);
                    }else {
                        consumer.setMessageModel(MessageModel.CLUSTERING);
                    }
                    //消费模式
                    if(rocketMqListener.receiveConfig().getConsumeFromWhere() !=null) {
                        consumer.setConsumeFromWhere( rocketMqListener.receiveConfig().getConsumeFromWhere() );
                    }
                    //设置 topic 消费tag
                    consumer.subscribe( rocketMqListener.receiveConfig().getTopic(), rocketMqListener.receiveConfig().getTag() );
                    consumer.setConsumerGroup( rocketMqListener.receiveConfig().getConsumerGroup() );
                    consumer.setMessageListener( rocketMqListener );
                    consumer.setConsumeMessageBatchMaxSize(rocketMqListener.receiveConfig().getConsumeMessageBatchMaxSize());
                    consumer.setInstanceName(rocketMqListener.receiveConfig().getConsumerGroup()+ UuidCreate.generateShortUuid() );
                    consumer.start();
                    log.info( "已初始化完成" + rocketMqListener.bindQueue().getQueueName() + "数据接收" );
                }
            }
        }catch (Exception e){
            log.error( "生成rocketmq监听失败 e={}",e );
        }
    }
}