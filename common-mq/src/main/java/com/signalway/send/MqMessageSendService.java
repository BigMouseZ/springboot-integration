package com.signalway.send;

import com.signalway.base.MessageSendRoutingKey;
import com.signalway.base.BaseMqMessage;


public interface MqMessageSendService<T extends BaseMqMessage> {
	
	public void send(T message,  String routingkey);

}
