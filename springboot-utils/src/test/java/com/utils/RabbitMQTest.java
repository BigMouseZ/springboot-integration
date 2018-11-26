package com.utils;

import com.utils.rabbitmqutils.RabbitMQUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by ZhangGang on 2018/11/24.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMQTest {
    @Autowired
    private RabbitMQUtils rabbitMQUtils;
    @Test
    public void rabbitMQtest(){

       // rabbitMQUtils.sendMQ(RoutingKey.PoliceMaintenanceSendCommand,RoutingKey.PoliceMaintenanceSendCommand,"消息测试");

        String json = "{\"beginDateToInt\":0,\"endDateToInt\":0,\"policecarid\":14326,\"policeid\":1,\"longitude\":108.407969,\"latitude\":22.671535,\"direction\":0.0,\"altitude\":500.0,\"gpsprecision\":0.0,\"speed\":16.0,\"collectiontime\":\"2018-11-16 17:58:25\",\"addtime\":\"2018-11-16 17:58:25\",\"usebygj\":1}";

        //System.out.println(rabbitMQUtils.sendAndReceive(RoutingKey.PoliceMaintenanceSendCommand,RoutingKey.PoliceMaintenanceSendCommand,"消息测试"));
    //    String receiveJson =  rabbitMQUtils.sendAndReceive("PoliceCarManage.CarGpsCollect", "PoliceCarManage.CarGpsCollect",json);
     //   System.out.println("发送GPS返回结果："+receiveJson);
    }
}
