package com.integration.springbootmq.mq.send;

import com.integration.springbootmq.mq.config.MessageSendRoutingKey;
import com.integration.springbootmq.mq.message.BaseMqMessage;

public interface MqMessageSendService<T extends BaseMqMessage> {
	
	public void send(T message, MessageSendRoutingKey routingkey);

}
