package com.signalway.mannager;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.stereotype.Service;
import com.signalway.utils.JsonPassUtil;

@Service
@Slf4j
public class MqMessageConverter implements MessageConverter{

	@Override
	public Message toMessage(Object object, MessageProperties messageProperties)
			throws MessageConversionException {
		byte[] jsondata ={};
		try{
			String json = JsonPassUtil.objectToJson(object);
			jsondata = json.getBytes("UTF-8");
			json = null;
		}catch(Exception e){
			log.error("MQ发送数据转换错误",e);
		}
		messageProperties.setContentType(MessageProperties.CONTENT_TYPE_JSON);
		messageProperties.setContentEncoding("UTF-8");
		messageProperties.setContentLength(jsondata.length);
		return new Message(jsondata, messageProperties);
	}

	@Override
	public Object fromMessage(Message message)
			throws MessageConversionException {
			return message.getBody();
	}

}
