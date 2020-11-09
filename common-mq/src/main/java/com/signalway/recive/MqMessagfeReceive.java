package com.signalway.recive;

import com.rabbitmq.client.Channel;
import com.signalway.base.*;
import com.signalway.mannager.MqManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import com.signalway.utils.JsonPassUtil;

import java.io.IOException;

@Slf4j
public abstract  class MqMessagfeReceive<T extends BaseMqMessage>  implements ChannelAwareMessageListener {
	public MqMessagfeReceive() {
		if(bindQueue() == null || receiveConfig() == null){
			log.error("MQ接收者设置有问题，将会影响数据接收");
		}else{
//			log.warn("正在注册MQ数据接收,队列名:"+bindQueue().getQueueName()+" 用途:"+bindQueue().getDeclaringClass()+" 客户端数量:"+receiveConfig().getConsumersCount()+" 最大提取数量:"+receiveConfig().getConsumersCount());
			MqManager.putQueue(bindQueue());
//			MqConfigManager.putQueueReceive(bindQueue().getQueueName(),receiveConfig());
			MqManager.putQueueReceivceService(bindQueue().getQueueName(), this);
			log.warn("已完成队列接收注册");
		}
	}
	/**
	 * 收到数据后如何处理的实现
	 * @param message
	 */
	public abstract Integer  onMessageData(T message);
	/**
	 * 设置此消息队列接收的队列数据
	 * @return
	 */
	public abstract MqQueueConfig bindQueue();
	/**
	 * 设置MQ接收消息的配置
	 * @return
	 */
	public abstract QueueConfg receiveConfig();
	/**
	 * 接收消息后转换出来的数据类型
	 * @return
	 */
	public  abstract Class<T> messageType();


    @Override
	public void onMessage(Message message, Channel channel)  {
		try {
			String json = new String(message.getBody(), "UTF-8");
			log.debug("收到MQ[{}]数据 json={}",bindQueue().getQueueName(),json);
			if (StringUtils.isNotBlank( json )) {
			  Integer status=	onMessageData(JsonPassUtil.josnToObject(json, messageType()));
			  //如果为手工确认模式，则按返回值确认消息消费成功
			  if(receiveConfig().getAcknowledgeMode()== AcknowledgeMode.MANUAL){
                   if(status==null ||status.intValue()!= SystemStatus.seccuss.getStatus()){
					   channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
				   }else {
					   channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
				   }
			  }
			}else{
				log.error("MQ[{}]收到的数据为空的",bindQueue().getQueueName());
				channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
			}

		} catch (Exception e) {
			log.error("MQ接收数据异常:",e);
		}

	}

}
