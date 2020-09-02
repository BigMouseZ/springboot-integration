package com.integration.springbootmq;

import com.integration.springbootmq.mq.config.MessageSendRoutingKey;
import com.integration.springbootmq.mq.send.MqMessageSendService;
import com.integration.springbootmq.mqlistener.VideoTrackMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootMqApplicationTests {
    @Resource
    private MqMessageSendService mqMessageSendService;

    @Test
    public void contextLoads() {
        VideoTrackMessage videoTrackMessage = new VideoTrackMessage();
        videoTrackMessage.setGuid(UUID.randomUUID().toString());
        mqMessageSendService.send(videoTrackMessage, MessageSendRoutingKey.EXPRESSWAY_CAPTURE_VIDEO_OUT_DATA_TEST);
    }

}
