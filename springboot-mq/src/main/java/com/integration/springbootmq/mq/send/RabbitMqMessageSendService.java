package com.integration.springbootmq.mq.send;

import com.integration.springbootmq.mq.config.MessageSendRoutingKey;
import com.integration.springbootmq.mq.message.BaseMqMessage;
import com.integration.springbootmq.mq.message.MqMessageConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitMqMessageSendService implements MqMessageSendService<BaseMqMessage>{
	 @Autowired(required=false)
	 private RabbitTemplate rabbitTemplate;  
	 @Autowired(required=false)
	 private MqMessageConverter mqMessageConverter;

	@Override
	public void send(BaseMqMessage message , MessageSendRoutingKey routingkey) {
		try {
			log.debug("开始发送Mq消息数据: key= "+routingkey.getRoutingKey()+" name = "+routingkey.getName());
//		rabbitTemplate.setExchange(exchangeName);
			rabbitTemplate.convertAndSend(routingkey.getRoutingKey(), message);
			System.out.println("...............");
		}catch (Exception ex){
			log.error("",ex);
		}

	}

}
