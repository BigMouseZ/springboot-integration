package com.signalway.send;

import com.alibaba.fastjson.JSONObject;
import com.signalway.base.BaseMqMessage;
import com.signalway.ex.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;

@Slf4j
@Service
public class RocketMqSendService {
    @Autowired(required = false)
    private RocketMQTemplate rocketMQTemplate;

    public void send(BaseMqMessage baseMqMessage, String key, String topic) {

        try {
            String jsonStr = JSONObject.toJSONString( baseMqMessage );
            if (jsonStr != null) {
                Message message = new Message( topic, key, jsonStr.getBytes("UTF-8") );
                rocketMQTemplate.getProducer().send( message );
            }
        } catch (Exception e) {
            throw new BusinessException( "发送mq失败 e={}", e );
        }
    }
}
