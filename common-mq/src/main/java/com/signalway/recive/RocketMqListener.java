package com.signalway.recive;


import com.alibaba.fastjson.JSONObject;
import com.signalway.base.BaseMqMessage;
import com.signalway.base.MqQueueConfig;
import com.signalway.base.QueueConfg;
import com.signalway.base.SystemStatus;
import com.signalway.mannager.MqManager;
import com.signalway.utils.JsonPassUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;


import java.util.List;

@Slf4j
public abstract class RocketMqListener<T extends BaseMqMessage> implements MessageListenerConcurrently {
    public RocketMqListener() {
        if (bindQueue() == null || receiveConfig() == null) {
            log.error( "rocketmq接收者设置有问题，将会影响数据接收" );
        } else {
//			log.warn("正在注册MQ数据接收,队列名:"+bindQueue().getQueueName()+" 用途:"+bindQueue().getDeclaringClass()+" 客户端数量:"+receiveConfig().getConsumersCount()+" 最大提取数量:"+receiveConfig().getConsumersCount());
            MqManager.putQueue( bindQueue() );
//			MqConfigManager.putQueueReceive(bindQueue().getQueueName(),receiveConfig());
            MqManager.putRocketMqListenerMap( this );
            log.warn( "已完成rocketmq队列接收注册" );
        }
    }

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        try {
            for (MessageExt message : msgs) {
                String json = new String( message.getBody(), "utf-8" );
                log.debug("rocketmq获得json数据={}",json  );
                if (StringUtils.isNotBlank( json )) {
                    Integer status = onMessageData( JSONObject.parseObject( json, messageType() ) );

                    if (status == null || status.intValue() == SystemStatus.seccuss.getStatus()) {
                        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                    } else {
                        return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                    }

                } else {
                    log.error( "MQ[{}]收到的数据为空的", bindQueue().getQueueName() );
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }

            }
        } catch ( Exception e) {
            log.error( "RocketMQ接收数据异常:", e );
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }

    /**
     * 收到数据后如何处理的实现
     *
     * @param message
     */
    public abstract Integer onMessageData(T message);

    /**
     * 设置此消息队列接收的队列数据
     *
     * @return
     */
    public abstract MqQueueConfig bindQueue();

    /**
     * 设置MQ接收消息的配置
     *
     * @return
     */
    public abstract QueueConfg receiveConfig();

    /**
     * 接收消息后转换出来的数据类型
     *
     * @return
     */
    public abstract Class<T> messageType();
}
