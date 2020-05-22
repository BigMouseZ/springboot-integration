package com.integration.springbootmq.mq.receive;

import com.integration.springbootmq.mq.JsonPassUtil;
import com.integration.springbootmq.mq.config.MqConfigManager;
import com.integration.springbootmq.mq.config.QueueMessageReceiveConfg;
import com.integration.springbootmq.mq.message.BaseMqMessage;
import com.integration.springbootmq.mq.queues.MqQueue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
@Slf4j
public abstract  class MqMessagfeReceive<T extends BaseMqMessage> implements  MessageListener{
	public MqMessagfeReceive() {
		if(bindQueue() == null || receiveConfig() == null){
			log.error("MQ接收者设置有问题，将会影响数据接收");
		}else{
//			log.warn("正在注册MQ数据接收,队列名:"+bindQueue().getQueueName()+" 用途:"+bindQueue().getDeclaringClass()+" 客户端数量:"+receiveConfig().getConsumersCount()+" 最大提取数量:"+receiveConfig().getConsumersCount());
			MqConfigManager.putQueue(bindQueue());
//			MqConfigManager.putQueueReceive(bindQueue().getQueueName(),receiveConfig());
			MqConfigManager.putQueueReceivceService(bindQueue().getQueueName(), this);
			log.warn("已完成队列接收注册");
		}
	}
	/**
	 * 收到数据后如何处理的实现
	 * @param message
	 */
	public abstract void onMessageData(T message);
	/**
	 * 设置此消息队列接收的队列数据
	 * @return
	 */
	public abstract MqQueue bindQueue();
	/**
	 * 设置MQ接收消息的配置
	 * @return
	 */
	public abstract QueueMessageReceiveConfg receiveConfig();
	/**
	 * 接收消息后转换出来的数据类型
	 * @return
	 */
	public  abstract Class<T> messageType();
	
	 @Override
	 public void onMessage(Message message) {
	     try {
	        log.debug("收到MQ[{}]数据",bindQueue().getQueueName());
	        String json = new String(message.getBody(), "UTF-8");
	        if (json != null) {
	        	onMessageData(JsonPassUtil.josnToObject(json, messageType()));
	        }else{
	            	log.error("MQ[{}]收到的数据时空的",bindQueue().getQueueName());
	        }
	    } catch (Exception e) {
	    	log.error("MQ接收数据异常:",e);
	    }
	 }


}
