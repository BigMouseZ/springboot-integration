package com.signalway.send;

import com.signalway.base.MessageSendRoutingKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.signalway.base.BaseMqMessage;


@Slf4j
@Service
public class RabbitMqMessageSendService implements MqMessageSendService<BaseMqMessage>{
	 @Autowired(required=false)
	 private RabbitTemplate rabbitTemplate;  


	@Override
	public void send(BaseMqMessage message , String routingkey) {
		log.debug("开始发送Mq消息数据: key= "+routingkey);
		
//		rabbitTemplate.setExchange(exchangeName);
		rabbitTemplate.convertAndSend(routingkey, message);
//		System.out.println("...............");
	}

}
