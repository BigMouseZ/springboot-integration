package com.signalway.mannager;


import com.signalway.base.MqQueue;
import com.signalway.base.MqQueueConfig;
import com.signalway.recive.MqMessagfeReceive;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.List;

@Slf4j
@Component
public class MqStartConfig implements ApplicationListener<ApplicationReadyEvent> {

    private final String exchangeName = "EXPRESSWAY_TOPIC";
    @Autowired(required = false)
    private RabbitTemplate rabbitTemplate;
    @Autowired(required = false)
    private MqMessageConverter mqMessageConverter;

//	private Map<String, SimpleMessageListenerContainer> listtions = new HashMap<String, SimpleMessageListenerContainer>();

    public void config() {
        if (rabbitTemplate == null) {
            log.info( "未开启rabbitmq" );
            return;
        }
        //构建队列
        try {
            //创建交换机
            rabbitTemplate.getConnectionFactory().createConnection().createChannel( false ).exchangeDeclare( exchangeName, "topic" );
            rabbitTemplate.setExchange( exchangeName );
            rabbitTemplate.setMessageConverter( mqMessageConverter );
            List<MqQueueConfig> queues = MqManager.allqueue();
            if (queues.size() > 0) {
                log.warn( "本次总共初始化队列个数 :" + queues.size() );
                for (MqQueueConfig queue : queues) {
                    rabbitTemplate.getConnectionFactory().createConnection().createChannel( false ).queueDeclare( queue.getQueueName(), true, false, false, null );
                    rabbitTemplate.getConnectionFactory().createConnection().createChannel( false ).queueBind( queue.getQueueName(), exchangeName, queue.getRoutkey() );
                    log.info( "队列:" + queue.getQueueName() + " 绑定:" + queue.getRoutkey() );
                }
            } else {
                log.warn( "未创建初始化任何队列" );
            }
            //
            //处理消息监听
//			 Map<String, QueueMessageReceiveConfg>  map =  MqConfigManager.allQueueConfig();
            List<MqMessagfeReceive> queueServices = MqManager.getAllQueueReceivceService();
            if (queueServices.size() > 0) {
                log.warn( "本次总共注册:" + queueServices.size() + "MQ对立监听器" );
                for (MqMessagfeReceive qs : queueServices) {
                    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer( rabbitTemplate.getConnectionFactory() );
                    container.setQueueNames( qs.bindQueue().getQueueName() );
                    container.setExposeListenerChannel( true );
                    //设置最大的并发的消费者数量
                    container.setMaxConcurrentConsumers( qs.receiveConfig().getConcurrentConsumers() );

                    //最小的并发消费者的数量
                    container.setConcurrentConsumers( 1 );
                    // container.setConcurrentConsumers(qs.receiveConfig().getConcurrentConsumers());
                    //设置确认模式自动
                    container.setAcknowledgeMode( qs.receiveConfig().getAcknowledgeMode() );
                    container.setMessageListener( qs );
//				      listtions.put(queuename, container);
                    container.start();


                    log.warn( "已初始化完成" + qs.bindQueue().getQueueName() + "数据接收" );
                }

            } else {
                log.warn( "MQ组件本次未注册任务队列监听器" );
            }


        } catch (Exception e) {

        }

    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent arg0) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        long st = System.currentTimeMillis();
        config();
        long et = System.currentTimeMillis();
        log.warn( "MQ服务初始化耗时:" + (et - st) + "毫秒" );
        stopWatch.stop();

    }


}
